CREATE TABLE Client (
  id INT AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(200) NOT NULL CHECK (LENGTH(name) >= 3 AND LENGTH(name) <= 200)
);

CREATE TABLE Planet (
  id VARCHAR(50) PRIMARY KEY,
  name VARCHAR(500) NOT NULL CHECK (LENGTH(name) >= 1 AND LENGTH(name) <= 500)
);

CREATE TABLE Ticket (
  id INT AUTO_INCREMENT PRIMARY KEY,
  createdAt TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
  clientId BIGINT,
  fromPlanetId VARCHAR(50),
  toPlanetId VARCHAR(50),
  FOREIGN KEY (clientId) REFERENCES Client(id),
  FOREIGN KEY (fromPlanetId) REFERENCES Planet(id),
  FOREIGN KEY (toPlanetId) REFERENCES Planet(id)
);