package com.example.onlineshop.domain

object StringConstants {

    //Authentication error messages
    const val emptyEmailErrorMessage = "Email can not be blank"
    const val invalidEmailErrorMessage = "Invalid Email"
    const val shortPasswordErrorMessage = "Password is too short"
    const val invalidPasswordErrorMessage = "Password must contain at least 1 digit and 1 letter"
    const val emptyUsernameErrorMessage = "This field can not be blank"

    //Authentication response messages
    const val registrationSuccessfulMessage = "Registration successful"
    const val userAlreadyExistsMessage = "User with this email already exists"
    const val loginSuccessfulMessage = "Login successful"
    const val loginFailedMessage = "Wrong email or password"
    const val userDoesNotExists = "User with this email does not exists"

    //SharedPreference params
    const val emailKey = "Email"

    //Network
    const val baseUrl = "https://run.mocky.io/v3/"

    //Ui error messages
    const val dataReceiveErrorMessage = "Data loading failed. Please try again"

    //Ui hep messages
    const val cantFindDataMessage = "We cant find anything"
    const val itemsInCart = "Items added in cart"
    const val addToFavoriteClicked = "Add to favorite clicked"
    const val shareClicked = "Share clicked"

    //Secondary text
    const val dollarChar = "$ "
    const val salePercentText = "% off"
    const val openBracket = "("
    const val closedBracket = ")"
    const val minimumEndPrice = "0"

    //Colors
    const val colorWhite = "#ffffff"
}