INSERT INTO users (id, username, sla_Late, sla_Tending_late, version, password, email, full_name, avatar_url)
VALUES
    (1, 'john_doe', 0, 0, 0, 'password123', 'john@example.com', 'John Doe', 'https://example.com/avatars/john.png'),
    (2, 'jane_smith', 0, 0, 0, 'password456', 'jane@example.com', 'Jane Smith', 'https://example.com/avatars/jane.png'),
    (3, 'alice_wonder', 0, 0, 0, 'password789', 'alice@example.com', 'Alice Wonder', 'https://example.com/avatars/alice.png'),
    (4, 'bob_builder', 0, 0, 0, 'password101', 'bob@example.com', 'Bob Builder', 'https://example.com/avatars/bob.png');

-- Friends Table
INSERT INTO friends (id, user_id, friend_id, sla_Late, sla_Tending_late, version)
VALUES
    (1, 'john_doe', 'jane_smith', 0, 0, 0),
    (2, 'jane_smith', 'john_doe', 0, 0, 0),
    (3, 'john_doe', 'alice_wonder', 0, 0, 0),
    (4, 'alice_wonder', 'john_doe', 0, 0, 0),
    (5, 'bob_builder', 'jane_smith', 0, 0, 0),
    (6, 'jane_smith', 'bob_builder', 0, 0, 0);

-- Messages Table
INSERT INTO messages (id, sender_id, receiver_id, content, content_type, sent_at, received_at, seen_at, sla_Late, sla_Tending_late, version)
VALUES
    (1, 'john_doe', 'jane_smith', 'Hello Jane!', 0, '2024-10-18 10:30:00', '2024-10-18 10:30:10', '2024-10-18 10:31:00', 0, 0, 0),
    (2, 'jane_smith', 'john_doe', 'Hi John!', 0, '2024-10-18 10:32:00', '2024-10-18 10:32:10', '2024-10-18 10:33:00', 0, 0, 0),
    (3, 'alice_wonder', 'john_doe', 'Hey John, how are you?', 0, '2024-10-18 11:00:00', '2024-10-18 11:00:10', '2024-10-18 11:01:00', 0, 0, 0),
    (4, 'john_doe', 'bob_builder', 'Hello Bob!', 0, '2024-10-18 11:05:00', '2024-10-18 11:05:10', '2024-10-18 11:06:00', 0, 0, 0);

-- Chatapp Table
INSERT INTO chatapp (id, users_id, sla_Late, sla_Tending_late, version)
VALUES
    (1, 1, 0, 0, 0),
    (2, 2, 0, 0, 0),
    (3, 3, 0, 0, 0), -- Ensure this user exists
    (4, 4, 0, 0, 0); -- Ensure this user exists