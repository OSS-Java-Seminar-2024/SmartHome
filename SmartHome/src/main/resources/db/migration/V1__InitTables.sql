CREATE TABLE Home (
                      id SERIAL PRIMARY KEY,
                      owner VARCHAR(50) NOT NULL,
                      location VARCHAR(50) NOT NULL,
                      rooms VARCHAR(50) NOT NULL
);

CREATE TABLE Users (
                      id SERIAL PRIMARY KEY,
                      username VARCHAR(50) NOT NULL,
                      password VARCHAR(255) NOT NULL,
                      role VARCHAR(50) NOT NULL
);

CREATE TABLE Room (
                      id SERIAL PRIMARY KEY,
                      home_id INT NOT NULL,
                      devices VARCHAR(50) NOT NULL,
                      FOREIGN KEY (home_id) REFERENCES Home(id)
);

CREATE TABLE Device (
                        id SERIAL PRIMARY KEY,
                        name VARCHAR(50) NOT NULL,
                        room_id INT NOT NULL,
                        status VARCHAR(20) NOT NULL,
                        type VARCHAR(20) NOT NULL,
                        FOREIGN KEY (room_id) REFERENCES Room(id)
);

CREATE TABLE HomeUsers (
                           home_id INT NOT NULL,
                           user_id INT NOT NULL,
                           PRIMARY KEY (home_id, user_id),
                           FOREIGN KEY (home_id) REFERENCES Home(id),
                           FOREIGN KEY (user_id) REFERENCES Users(id)
);

CREATE TABLE Interface (
                           id SERIAL PRIMARY KEY,
                           device_id INT NOT NULL,
                           input VARCHAR(50) NOT NULL,
                           output VARCHAR(50) NOT NULL,
                           FOREIGN KEY (device_id) REFERENCES Device(id)
);

CREATE TABLE Logs (
                      id SERIAL PRIMARY KEY,
                      device_id INT NOT NULL,
                      action VARCHAR(50) NOT NULL,
                      timestamp TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
                      origin VARCHAR(50) NOT NULL,
                      FOREIGN KEY (device_id) REFERENCES Device(id)
);

CREATE TABLE Schedule (
                          id SERIAL PRIMARY KEY,
                          home_id INT NOT NULL,
                          start_time TIMESTAMP NOT NULL,
                          end_time TIMESTAMP NOT NULL,
                          repeat VARCHAR(50) NOT NULL,
                          purpose VARCHAR(50) NOT NULL,
                          FOREIGN KEY (home_id) REFERENCES Home(id)
);