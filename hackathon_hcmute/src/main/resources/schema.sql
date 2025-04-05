
CREATE TABLE IF NOT EXISTS account (
                         account_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                         username VARCHAR(255),
                         fullname VARCHAR(255),
                         password VARCHAR(255)
);

-- Bảng câu hỏi
CREATE TABLE IF NOT EXISTS question (
                          question_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                          question_content VARCHAR(255),
                          type VARCHAR(255)
);

-- Bảng câu trả lời
CREATE TABLE IF NOT EXISTS answer (
                        answer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        question_id BIGINT,
                        answer_content VARCHAR(255),
                        FOREIGN KEY (question_id) REFERENCES question(question_id)
);

-- Bảng kết quả
CREATE TABLE IF NOT EXISTS result (
                        result_id BIGINT AUTO_INCREMENT PRIMARY KEY,
                        account_id BIGINT,
                        result_content VARCHAR(255),
                        FOREIGN KEY (account_id) REFERENCES account(account_id)
);