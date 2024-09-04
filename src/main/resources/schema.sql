-- Create the 'categories' table if it doesn't exist
CREATE TABLE IF NOT EXISTS categories (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    image_url VARCHAR(255)
);

-- Create the 'products' table if it doesn't exist
CREATE TABLE IF NOT EXISTS products (
    id SERIAL PRIMARY KEY,
    category_id INT NOT NULL REFERENCES categories(id) ON DELETE CASCADE,
    name VARCHAR(255) NOT NULL,
    description TEXT,
    price NUMERIC(10, 2),
    release_date DATE,
    image_url VARCHAR(255),
    on_sale BOOLEAN
);

CREATE TABLE IF NOT EXISTS orders (
    id SERIAL PRIMARY KEY,
    identity_user_id VARCHAR(255) NOT NULL,
    order_date_time TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    total_cost NUMERIC(10, 2) NOT NULL,
    status VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS order_items (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    product_id INT NOT NULL REFERENCES products(id) ON DELETE CASCADE,
    quantity INT NOT NULL,
    unit_price NUMERIC(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS payments (
    id SERIAL PRIMARY KEY,
    order_id INT NOT NULL REFERENCES orders(id) ON DELETE CASCADE,
    payment_method VARCHAR(255),
    payment_date DATE,
    amount NUMERIC(10, 2) NOT NULL
);

CREATE TABLE IF NOT EXISTS reviews (
    id SERIAL PRIMARY KEY,
    product_id INT NOT NULL REFERENCES products(id) ON DELETE CASCADE,
    identity_user_id VARCHAR(255) NOT NULL,
    rating INT NOT NULL,
    review_comment VARCHAR(200) NOT NULL
);

