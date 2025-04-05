SET FOREIGN_KEY_CHECKS=0;

DROP TABLE IF EXISTS `account`;
DROP TABLE IF EXISTS `question`;
DROP TABLE IF EXISTS `answer`;
DROP TABLE IF EXISTS `result`;

SET FOREIGN_KEY_CHECKS=1;

CREATE TABLE account (
                         account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         username VARCHAR(255),
                         fullname VARCHAR(255),
                         password VARCHAR(255)
);

-- Bảng câu hỏi
CREATE TABLE question (
                          question_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          question_content VARCHAR(255),
                          type VARCHAR(255)
);

-- Bảng câu trả lời
CREATE TABLE answer (
                        answer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        question_id BIGINT,
                        answer_content VARCHAR(255),
                        FOREIGN KEY (question_id) REFERENCES question(question_id)
);

-- Bảng kết quả
CREATE TABLE result (
                        result_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        account_id BIGINT,
                        result_content VARCHAR(255),
                        FOREIGN KEY (account_id) REFERENCES account(account_id)
);