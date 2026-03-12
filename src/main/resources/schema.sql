DROP TABLE IF EXISTS booking;
DROP TABLE IF EXISTS room;

CREATE TABLE room (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    name TEXT NOT NULL,
    building TEXT NOT NULL,
    room_number TEXT NOT NULL,
    capacity INTEGER NOT NULL,
    whiteboard BOOLEAN NOT NULL
);

CREATE TABLE booking (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    room_id INTEGER NOT NULL,
    date TEXT NOT NULL,
    start_time TEXT NOT NULL,
    end_time TEXT NOT NULL,
    FOREIGN KEY (room_id) REFERENCES room(id)
);