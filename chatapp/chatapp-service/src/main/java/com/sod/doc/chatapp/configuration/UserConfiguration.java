package com.sod.doc.chatapp.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sod.doc.chatapp.configuration.dao.FriendsRepository;
import com.sod.doc.chatapp.configuration.dao.UserRepository;
import com.sod.doc.chatapp.service.AddFriendUserService;
import com.sod.doc.chatapp.service.CreateUserService;
import com.sod.doc.chatapp.service.cmds.AddFriendUserCommand;
import com.sod.doc.chatapp.service.cmds.CreateUserInChatAppCommand;
import com.sod.doc.chatapp.service.cmds.handler.HandleConnectionService;
import com.sod.doc.chatapp.service.cmds.handler.HandleUserConnectionService;
import com.sod.doc.chatapp.service.cmds.handler.SessionManagementService;
import com.sod.doc.chatapp.service.cmds.impl.AddFriendUserServiceImpl;
import com.sod.doc.chatapp.service.cmds.impl.CreateUserServiceImpl;
import com.sod.doc.chatapp.service.store.FriendsEntityStore;
import com.sod.doc.chatapp.service.store.UserEntityStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserConfiguration {
    @Bean
    public CreateUserService createUserService(UserEntityStore userEntityStore) {
        return new CreateUserServiceImpl(userEntityStore);
    }

    @Bean
    @Autowired
    HandleConnectionService handleConnectionService(FriendsEntityStore friendsEntityStore, ObjectMapper objectMapper,SessionManagementService sessionManagementService) {
        return new HandleConnectionService(friendsEntityStore,sessionManagementService,objectMapper);
    }

    @Bean
    @Autowired
    HandleUserConnectionService handleUserConnectionService(UserEntityStore userEntityStore, SessionManagementService sessionManagementService) {
        return new HandleUserConnectionService(userEntityStore,
                sessionManagementService);
    }

    @Bean
    SessionManagementService sessionManagementService() {
        return new SessionManagementService();
    }

    @Bean
    @Autowired
    public CreateUserInChatAppCommand createUserInChatAppCommand( CreateUserService createUserService) {
        return new CreateUserInChatAppCommand(createUserService);
    }

    @Bean
    @Autowired
    public UserEntityStore customUserEntityStore(UserRepository userRepository) {
        return new UserEntityStore(userRepository);
    }

    @Bean
    @Autowired
    public AddFriendUserService addFriendUserService(UserEntityStore userEntityStore, FriendsEntityStore friendsEntityStore) {
        return new AddFriendUserServiceImpl(userEntityStore, friendsEntityStore);
    }

    @Bean
    @Autowired
    public FriendsEntityStore friendsEntityStore(FriendsRepository friendsRepository)
    {
        return new FriendsEntityStore(friendsRepository);
    }
    @Bean
    AddFriendUserCommand addFriendUserCommand(AddFriendUserService addFriendUserService) {
        return new AddFriendUserCommand(addFriendUserService);
    }

}
