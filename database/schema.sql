CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    nama VARCHAR(100) NOT NULL,
    email VARCHAR(150) UNIQUE NOT NULL,
    password_hash TEXT NOT NULL,
    pin_hash TEXT,
    role VARCHAR(10) NOT NULL DEFAULT 'user',
    is_active BOOLEAN NOT NULL DEFAULT TRUE,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE catatan (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    judul VARCHAR(100) NOT NULL,
    isi TEXT,
    tipe VARCHAR(15) NOT NULL DEFAULT 'teks' CHECK (tipe IN ('teks', 'checklist')),
    warna VARCHAR(20) NOT NULL DEFAULT '#FFFFFF',
    is_pinned BOOLEAN NOT NULL DEFAULT FALSE,
    is_archived BOOLEAN NOT NULL DEFAULT FALSE,
    is_locked BOOLEAN NOT NULL DEFAULT FALSE,
    lampiran VARCHAR(255),
    deleted_at TIMESTAMP NULL,
    created_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP
);

CREATE TABLE label (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    nama VARCHAR(30) NOT NULL,
    warna VARCHAR(20) NOT NULL DEFAULT '#E0E0E0',
    UNIQUE (user_id, nama)
);

CREATE TABLE catatan_label (
    catatan_id INT NOT NULL REFERENCES catatan(id) ON DELETE CASCADE,
    label_id INT NOT NULL REFERENCES label(id) ON DELETE CASCADE,
    PRIMARY KEY (catatan_id, label_id)
);

CREATE TABLE checklist_item (
    id SERIAL PRIMARY KEY,
    catatan_id INT NOT NULL REFERENCES catatan(id) ON DELETE CASCADE,
    teks VARCHAR(200) NOT NULL,
    is_checked BOOLEAN NOT NULL DEFAULT FALSE,
    urutan INT NOT NULL DEFAULT 0
);

CREATE TABLE pengingat (
    id SERIAL PRIMARY KEY,
    catatan_id INT NOT NULL REFERENCES catatan(id) ON DELETE CASCADE,
    waktu_pengingat TIMESTAMP NOT NULL,
    keterangan VARCHAR(100),
    is_active BOOLEAN NOT NULL DEFAULT TRUE
);

CREATE TABLE refresh_tokens (
    id SERIAL PRIMARY KEY,
    user_id INT NOT NULL REFERENCES users(id) ON DELETE CASCADE,
    token_hash TEXT NOT NULL,
    expires_at TIMESTAMP NOT NULL
);