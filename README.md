# Food App 🥗 

This mobile application allows users to search for products, manage orders, and interact with categories. 
It features an intuitive and responsive design, ensuring smooth navigation even on varying screen sizes. 
The app integrates with mock APIs to fetch categories and product data, providing real-time updates. 
The app stores product and category data locally, ensuring a seamless experience when searching, filtering, and managing cart items.

# Features 📱 

Search Products:

Users can search for products by name using a search bar.
The search filters products dynamically based on the entered text, displaying matching results in real time.
Order Management:

Products can be added to the cart by tapping them directly.
The "View Order" button at the bottom of the screen displays the total price and quantity of products in the cart.
When the user presses the "View Order" button, the order is cleared, resetting the cart data for a fresh order.
Cart data is saved in Room, ensuring that the products and order details are preserved even when searching, filtering, and navigating through different sections of the app.
Working API Integration:

The app integrates with mock APIs (e.g., Mockaroo) to fetch categories and product data.
API integration allows the app to display a list of categories and their respective products, which are stored in the Room for offline use.
Design and Responsiveness:

The app is designed to work in portrait mode only, optimizing user experience for mobile devices.
UI elements are responsive, adjusting automatically to fit different screen sizes and resolutions, providing a consistent and visually appealing layout across various Android devices.

Support Dark Theme


# Technologies Used 🛠️ 

The app is built using modern Android development tools and practices, following the MVVM Clean Architecture pattern. Here's a breakdown of the technologies used:   

• Kotlin<br />
• Compose<br />
• MVVM Clean Architecture<br />
• Ktor<br />
• Gson<br />
• Koin<br />
• Coroutines<br />
• State Flow<br />
• Coil<br />
• ROOM<br />
• Chucker<br />
• Lottie Animation<br />
• Junit<br />
• Mockk<br />
• Turbine<br />

# Securing Api Key 👮🏻
• To secure the API key in this project, we used the BuildConfigField approach, which injects the API key during the build process. The key is stored securely in the local.properties file, which is not tracked by version control, and is injected into the app at build time. This avoids hardcoding the key directly into the source code, making it more secure.

Additional Security Options:

• **Encrypted SharedPreferences:** Securely stores sensitive data, such as API keys, on the device by encrypting it before storing it. However, it can still be accessed if the device is compromised.

• **NDK (Native Development Kit):** Stores sensitive data in native C++ code, making it more difficult to reverse-engineer. It offers an added layer of security but is not entirely foolproof against skilled attackers.

# Supported Android Version ℹ
• Android 9 (Api 28) and above

# Test Coverage 🧪

This project includes comprehensive **unit tests** and **UI tests** to ensure the robustness and reliability of the application:

• **Unit Tests** <br />
Implemented unit tests for almost all features and scenarios of the application.
These tests cover core business logic, data transformations, and edge cases to ensure the app behaves as expected across different use cases.

• **UI Tests** <br />
The project also includes extensive UI test cases for the Home Screen using Jetpack Compose Testing.
By covering both unit and UI testing, we aim to provide a reliable user experience and maintain the app's quality over time.

## Demo Video
Light Theme
<p>
  <img src="demo_light_2.gif" height="500" width="250">
</p>

Dark Theme
<p>
  <img src="demo_gif/demo_dark.gif" height="500" width="250">
</p>


# Screen Shots 🖼
<p align="top">
<img src = "screenshot/tables_shimmer_light.jpg"  height="400" width = "200">
<img src = "screenshot/tables_all_light.png"  height="400" width = "200">
<img src = "screenshot/tables_brekfast_cart_data_light.jpg" height="400" width = "200">
<img src = "screenshot/tables_launch_cart_data_light.jpg" height="400" width = "200">
<br />
<br />
<img src = "screenshot/tables_filter_with_empty_results.jpg" height="400" width = "200">
<img src = "screenshot/tables_search_light_pasta.jpg" height="400" width = "200">
<img src = "screenshot/tables_no_search_result.jpg" height="400" width = "200">
<img src = "screenshot/tables_search_keyboard_open.jpg" height="400" width = "200">
<br />
<br />
<img src = "screenshot/tables_error_section.jpg" height="400" width = "200">
<img src = "screenshot/menu_ligth.jpg" height="400" width = "200">
<img src = "screenshot/order_light.jpg" height="400" width = "200">
<img src = "screenshot/settings_light.jpg" height="400" width = "200">

<br />
<br />
