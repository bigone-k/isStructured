-- User
DROP TABLE IF EXISTS User;
CREATE TABLE IF NOT EXISTS User (
      userNo     BIGINT AUTO_INCREMENT,
      userId     VARCHAR(100) NOT NULL,
      password   VARCHAR(300) NOT NULL,
      name       VARCHAR(100) NOT NULL,
      authority  VARCHAR(50) NOT NULL DEFAULT 'ROLE_USER',
      provider   VARCHAR(100),

      PRIMARY KEY(userNo)
);
