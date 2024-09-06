package com.sod.doc.contentreader.service.cmds;

import com.sod.doc.contentreader.exception.ContentReaderException;
import com.sod.doc.contentreader.model.Contentreader;
import com.sod.doc.contentreader.CreateShortestUrlPayLoad;
import com.sod.doc.contentreader.service.store.ContentreaderEntityStore;
import org.apache.commons.lang3.RandomStringUtils;
import org.chenile.stm.STMInternalTransitionInvoker;
import org.chenile.stm.State;
import org.chenile.stm.action.STMTransitionAction;
import org.chenile.stm.model.Transition;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Random;


public class CreateShortestUrlService implements STMTransitionAction<Contentreader> {

    Logger logger = LoggerFactory.getLogger(CreateShortestUrlService.class);
    final static String HTTP = "http://";
    final static String HTTPS = "https://";
    @Value("${url.prefix.link}")
    private String HTTP_PREFIX;

    @Autowired
    private ContentreaderEntityStore contentreaderEntityStore;


    @Override
    public void doTransition(Contentreader contentreader, Object payload, State startState, String eventId, State endState, STMInternalTransitionInvoker<?> stm, Transition transition) throws Exception {
        /*TODO:
        Given Url I have to validate Given URL is Valid
        if no throw its not valid
        if Yes continue
        */
        if (payload instanceof CreateShortestUrlPayLoad pd) {
            String oUrl = pd.getOriginalUrl();
            if (!this.validateURL(oUrl)) {
                throw new ContentReaderException(oUrl);
            }
            contentreader.setOriginalUrl(oUrl);
            String sUrl = this.convertToShort(contentreader);
            sUrl = this.addPrefixToShortUrl(sUrl);
            if (contentreaderEntityStore.findByShortUrl(sUrl)!=null) {
                logger.warn("Shortest Path got matched Change the Logic");
                this.makeIdenticalShortestPath(contentreader);
            }
            contentreader.setShortestUrl(sUrl);
            contentreader.setTimePeriod(pd.getTimePeriod());
            contentreader.setFullName(pd.getFullName());
        }
    }

    private void makeIdenticalShortestPath(Contentreader contentreader) {
        /*TODO*/
    }

    private String addPrefixToShortUrl(String sUrl) {
        return HTTP_PREFIX + sUrl;
    }

    protected String convertToShort(Contentreader contentreader) {
        return generateShortURL(contentreader);
    }

    private String generateShortURL(Contentreader contentreader) {
        int lengthOfShortString = this.findOutLength(contentreader);
        return RandomStringUtils.randomAlphabetic(lengthOfShortString);
    }

    private int findOutLength(Contentreader contentreader) {
        int l = contentreader.getOriginalUrl().length();
        Random random = new Random();

        if (l < 15) {
            return random.nextInt(4) + 8;
        } else if (l < 30) {
            return random.nextInt(5) + 10;
        } else {
            return random.nextInt(6) + 10;
        }
    }


    private boolean validateURL(String oUrl) {

        try {
            URI url = new URI(oUrl);
            URL validUrl = url.toURL();
            String protocols = validUrl.getProtocol();
            return protocols != null && !protocols.startsWith(HTTP) && !protocols.startsWith(HTTPS);
        } catch (URISyntaxException | MalformedURLException e) {
            return false;
        }
    }
}
