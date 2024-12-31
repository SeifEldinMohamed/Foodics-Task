# Foodics App 🥗 

This mobile application allows users to search for products, manage orders, and interact with categories. 
It features an intuitive and responsive design, ensuring smooth navigation even on varying screen sizes. 
The app integrates with mock APIs to fetch categories and product data, providing real-time updates. 
The app stores product and category data locally, ensuring a seamless experience when searching, filtering, and managing cart items.

# Features 📱 

Search Products <br />
&nbsp;&nbsp;&nbsp;&nbsp;• Users can search for products by name using an intuitive search bar.<br />
&nbsp;&nbsp;&nbsp;&nbsp;• The search dynamically filters products based on the entered text.<br />
&nbsp;&nbsp;&nbsp;&nbsp;• Matching results are displayed in real time for a seamless user experience.<br />
  
Order Management <br />
• Products can be added to the cart by tapping on them directly and saved in database to keep cart data even when user kill the app.<br />
&nbsp;&nbsp;&nbsp;&nbsp;• The "View Order" button at the bottom of the screen:<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Displays the total price and quantity of products in the cart.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Clears the order when pressed, resetting the cart data for a new order.<br />
&nbsp;&nbsp;&nbsp;&nbsp;• Cart data is stored in a Room database, ensuring:<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Products and order details persist across search, filter, and navigation.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- A consistent experience even after app relaunch.<br />
    
API Integration<br />
  • The app integrates with mock APIs (Mockaroo) to fetch:<br />
&nbsp;&nbsp;&nbsp;&nbsp;- Products List<br />
&nbsp;&nbsp;&nbsp;&nbsp;- Categorys List<br />
&nbsp;&nbsp;&nbsp;&nbsp;- Fetched data is stored in the Room database for offline use.<br />
    
Design and Responsiveness<br />
&nbsp;&nbsp;&nbsp;&nbsp;• Designed exclusively for portrait mode, optimizing the user experience for mobile devices.<br />
&nbsp;&nbsp;&nbsp;&nbsp;• UI elements are fully responsive, ensuring:<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Consistent layouts across different screen sizes and resolutions.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- A visually appealing experience on a variety of Android devices.<br />
    
Dark Theme Support<br />
&nbsp;&nbsp;&nbsp;&nbsp;• The app fully supports Dark Theme, providing:<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- Enhanced usability in low-light conditions.<br />
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;- A modern and visually pleasing aesthetic for users.<br />


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
The project also includes extensive UI test cases for the Tables Screen using Jetpack Compose Testing.
By covering both unit and UI testing, we aim to provide a reliable user experience and maintain the app's quality over time.

## Demo Videos 🎬
Light Theme
<p>
  <img src="demo_light_2.gif">
</p>

Dark Theme
<p>
  <img src="demo_gif/demo_dark.gif" height="500" width="250">
</p>


# Screen Shots 🖼
<p align="top">
<img src = "screenshot/tables_shimmer_light.jpg"  height="400" width = "200">
<img src = "screenshot/tables_main_light.jpeg"  height="400" width = "200">
<img src = "screenshot/tables_brekfast_cart_data_light.jpg" height="400" width = "200">
<img src = "screenshot/tables_launch_cart_data_light.jpg" height="400" width = "200">
<br />
<br />
<img src = "screenshot/tables_filter_with_empty_results.jpg" height="400" width = "200">
<img src = "screenshot/tables_search_light_pasta.jpg" height="400" width = "200">
<img src = "screenshot/tables_search_pa_keyboard_closed.jpeg" height="400" width = "200">
<img src = "screenshot/tables_search_no_results_light.jpeg" height="400" width = "200">
<br />
<br />
<img src = "screenshot/tables_error_section.jpg" height="400" width = "200">
<img src = "screenshot/menu_ligth.jpg" height="400" width = "200">
<img src = "screenshot/order_light.jpg" height="400" width = "200">
<img src = "screenshot/settings_light.jpg" height="400" width = "200">

<br />
<br />
<img src = "screenshot/tables_shimmer_dark.jpg" height="400" width = "200">
<img src = "screenshot/tables_dark.jpg" height="400" width = "200">
<img src = "screenshot/tables_breakfast_filter_dark.jpg" height="400" width = "200">
<img src = "screenshot/tables_Launch_filter_dark.jpg" height="400" width = "200">

<br />
<br />
<img src = "screenshot/tables_filter_no_results_dark.jpeg" height="400" width = "200">
<img src = "screenshot/tables_search_keyboard_open.jpg" height="400" width = "200">
<img src = "screenshot/tables_search_keyboard_closed.jpg" height="400" width = "200">
<img src = "screenshot/tables_search_no_results_dark.jpeg" height="400" width = "200">

<br />
<br />
<img src = "screenshot/tables_error_section_dark.jpg" height="400" width = "200">
<img src = "screenshot/menu.jpg" height="400" width = "200">
<img src = "screenshot/orders.jpg" height="400" width = "200">
<img src = "screenshot/settings.jpg" height="400" width = "200">

<br />
<br />
<img src = "screenshot/tablet_animation_light.png" height="550" width = "400">
<img src = "screenshot/tablet_light.png" height="550" width = "400">
<br />
<br />
<img src = "screenshot/tablet_with_cart_data_light.png" height="550" width = "400">
<img src = "screenshot/tablet_filter_animation_light.png" height="550" width = "400">
<br/>
<br/>
<img src = "screenshot/tablet_breakfast.png" height="550" width = "400">
<img src = "screenshot/tablet_launch.png" height="550" width = "400">

<br />
<br />
<img src = "screenshot/tablet_animation_dark.png" height="550" width = "400">
<img src = "screenshot/tablet_dark.png" height="550" width = "400">
<br/>
<br/>
<img src = "screenshot/tablet_with_cart_data_light.png" height="550" width = "400">
<img src = "screenshot/tablet_animation_dark.png" height="550" width = "400">
<br/>
<br/>
<img src = "screenshot/tablet_breakfast_dark.png" height="550" width = "400">
<img src = "screenshot/tablet_launch_dark.png" height="550" width = "400">
<br />
<br />
