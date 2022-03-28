# GitHub Api App

This is a simple Android application which makes a request to an API, parses the response, and displays the result in the UI. The app will consist of two major components - one search component and one results component.

The App allows a user to make a request to search for users and recieve results from the github Api.
The request is made via this url

'''
https://api.github.com/search/users?q={searchString} in:searchString
'''

Where 'searchString' is the user entered Search Term.

The App uses pagination, with 9 items displayed per page, the retrofit library for making the Api calls, and kotlin coroutines.
The App uses the MVVM architecture.
The App includes intergration tests for the Api.

# Built With
* Android Studio
* Kotlin
* Gradle

# Libraries
* Glide 
* Retrofit 
* Kotlin Coroutines

