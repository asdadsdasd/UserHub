INSERT INTO users (username, password, unique_text, image_path)
VALUES
    ('alice', '$2a$10$.UQcmGoEElkmD1dpcWeLeuOGeYZLlMRxeeBarAcRjHzisTaPxhUJW', 'Hello Alice', 'user1.jpg'),
    ('bob', '$2a$10$IkvvwXvujXLFmbYPiApijOSa9d0gzcpnEvXXVD95/u9LORJeg2WLW', 'Hello Bob', 'user2.jpg'),
    ('carol', '$2a$10$2ZR1za9uI2a4WUpLbqkrwuUokSwO6pNAV1uwOaHC2hIdbeVF1usl6', 'Hello Carol', 'user3.jpg')
    ON CONFLICT (username) DO NOTHING;