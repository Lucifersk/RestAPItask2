# 🌦️ Weather Forecast CLI App (Java)

This is a simple **Java-based console application** that allows users to get **real-time weather information** for any city in the world 🌍 using the [Open-Meteo Weather API](https://open-meteo.com/) and OpenStreetMap's Nominatim geolocation API.

---

## 📸 Preview
<img width="817" alt="starting" src="https://github.com/user-attachments/assets/6bbf4395-76da-44b8-a091-be2ba03e5980" />
<img width="765" alt="for yes" src="https://github.com/user-attachments/assets/f0c5a905-4172-4099-a35c-72f14dd3c0c5" />
<img width="662" alt="if the user say no" src="https://github.com/user-attachments/assets/6250aeb2-5baf-44e2-9b47-5795a4fafd57" />


Thank you for using the Weather Forecast App. Stay safe!


## 🚀 Features

- 🌐 Input **any city** name
- 🗺️ Automatically fetches **latitude & longitude**
- ☀️ Retrieves **real-time temperature and wind speed**
- 🕒 Displays **formatted local time** (`dd MMMM yyyy, hh:mm a`)
- 🔁 Option to **check multiple cities** without restarting the app
- 🛡️ Handles HTTP errors and invalid city input

---

## 🧰 Technologies Used

- Java 8+
- OpenStreetMap Nominatim API (for geocoding)
- Open-Meteo API (for current weather)
- `org.json` library (for JSON parsing)

---

## 📦 Setup Instructions

### 1. Clone the Repository
```bash
git clone https://github.com/Lucifersk/RestAPItask2/new/main?filename=README.md
cd weather-forecast-java
2. Download the org.json Library
Download the .jar file from:
👉 https://mvnrepository.com/artifact/org.json/json

Then add it to your project:

In IntelliJ IDEA:
File > Project Structure > Libraries > + Add JAR

▶️ How to Run
Open the project in your favorite Java IDE (e.g. IntelliJ, Eclipse)

Compile and run the Apitask2 class

Enter any city name and view its weather

Type yes to check another city or no to exit

✅ Example Cities to Try
Delhi

Tokyo

New York

Sydney

Paris

💡 Notes
Internet connection is required to access external APIs.

The time zone is manually set to "Asia/Kolkata". You can change it or use timezone=auto in the API for dynamic time zones.

🙌 Acknowledgements
Open-Meteo Weather API
Nominatim Geocoding API

#Author name
Sumit kumar
