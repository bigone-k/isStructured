DROP TABLE IF EXISTS PreAgree;
DROP TABLE IF EXISTS IDCard;
DROP TABLE IF EXISTS AuthWord;
DROP TABLE IF EXISTS ProcessStepHistory;
DROP TABLE IF EXISTS ProcessStep;
DROP TABLE IF EXISTS User;
DROP TABLE IF EXISTS DuplicationManager;
DROP TABLE IF EXISTS OtherAccountTrans;
DROP TABLE IF EXISTS StateCode;
DROP TABLE IF EXISTS ProcessStepType;

CREATE TABLE User (
                      userNo     BIGINT AUTO_INCREMENT,
                      userId     VARCHAR(100) NOT NULL,
                      password   VARCHAR(300) NOT NULL,
                      name       VARCHAR(100) NOT NULL,
                      authority  VARCHAR(50) NOT NULL DEFAULT 'ROLE_USER',
                      provider   VARCHAR(100),

                      PRIMARY KEY(userNo)
);

CREATE TABLE PreAgree
(
    seqNo           BIGINT AUTO_INCREMENT,
    userNo          BIGINT,
    agree           TINYINT(1) NOT NULL DEFAULT 1,
    accountPassword VARCHAR(4),

    PRIMARY KEY(seqNo),
    FOREIGN KEY(userNo) REFERENCES User(userNo)
);

CREATE TABLE IDCard
(
    seqNo           BIGINT AUTO_INCREMENT,
    userNo          BIGINT,
    name            VARCHAR(100) NOT NULL,
    birthday        VARCHAR(20) NOT NULL,
    idCardSeqNo     VARCHAR(50) NOT NULL,

    PRIMARY KEY(seqNo),
    FOREIGN KEY(userNo) REFERENCES User(userNo)
);

CREATE TABLE DuplicationManager
(
    jwtToken     VARCHAR(300) NOT NULL,

    PRIMARY KEY(jwtToken)
);

CREATE TABLE AuthWord
(
    seqNo       BIGINT AUTO_INCREMENT,
    userNo      BIGINT,
    authWord    VARCHAR(8),
    regDate     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY(seqNo),
    FOREIGN KEY(userNo) REFERENCES User(userNo)
);

CREATE TABLE ProcessStep
(
    seqNo       BIGINT AUTO_INCREMENT,
    userNo      BIGINT,
    stepType    TINYINT,
    stateCode   TINYINT,
    regDate     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY(seqNo),
    FOREIGN KEY(userNo) REFERENCES User(userNo)
);

CREATE TABLE ProcessStepHistory
(
    seqNo       BIGINT AUTO_INCREMENT,
    mstSeqNo    BIGINT,
    stepType    TINYINT,
    stateCode   TINYINT,
    regDate     DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY(seqNo),
    FOREIGN KEY(mstSeqNo) REFERENCES ProcessStep(seqNo)
);

CREATE TABLE ProcessStepType
(
    stepType    TINYINT,
    stepDesc    VARCHAR(50),

    PRIMARY KEY(stepType)
);

CREATE TABLE StateCode
(
    stateCode    TINYINT,
    stateDesc    VARCHAR(50),

    PRIMARY KEY(stateCode)
);

CREATE TABLE OtherAccountTrans
(
    seqNo           BIGINT AUTO_INCREMENT,
    accountName     BIGINT,
    accountNum      TINYINT,
    bankCode        TINYINT,
    stateCode       TINYINT,
    regDate         DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP,

    PRIMARY KEY(seqNo)
);
