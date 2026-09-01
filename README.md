# Football Kit Store

A desktop Java Swing app for browsing and ordering football kit — jerseys, boots, footballs, and more — with live stock tracking and a persistent order history.

## Features

- **Product catalog** — six items (Football, Jersey, Shorts, Watersipper, Scarf, Boots), each with an image, price, and live stock count
- **Quantity selection** — per-item dropdown capped at current stock, so you can't order more than what's available
- **Running total** — Save recalculates the total price across all selected items
- **Order confirmation** — Confirm validates stock, asks for a yes/no confirmation, then deducts the ordered quantities from stock
- **Persistent order history** — confirmed orders are appended to `orders/order_history.txt` and reloaded into the on-screen log on every launch
- **A button you shouldn't press** — self-explanatory

## Tech Stack

- Java (Swing / AWT) for the UI
- Plain file I/O for order persistence — no database
- Built and run via IntelliJ IDEA

## Project Structure

```
Football Kit Management App/
├── Main.java              # entry point
├── FootballKitStore.java  # UI + order logic
├── Kit images/            # product images
├── BlueFootball/          # window icon
├── skull/                 # icon for the joke button
└── orders/                # order_history.txt gets written here
```

## Running It

Open the project in IntelliJ IDEA (or compile manually with `javac`), then run `Main.java`. Make sure the working directory is set to `Football Kit Management App/` so the image and order-history paths resolve correctly.

## Notes

This was an early Java/OOP practice project — a store interface backed by arrays and basic Swing components rather than a database.
