# github-user
You can search users on Github and add them as favorite if you want

## Requirements

In this app, you can search users on Github and add them as favorite if you want.
- Github user query API (https://developer.github.com/v3/search/#search-users) is used to search users
- Favorite users are saved in a local database
- You can search favorite users on the local database

### Github user search screen

Requirements are as follows:
- you can type a query in the search box, and then search result is shown in the list when you click the search button. Only name field is used when invoking Github user search API
- the number of items in search result is 100 at most. To do so, page is set to 1 and per_page to 100 when invoking API, . Please consult Github API documentation for more detail
- each user's information includes profile image, name, and whether this user is your favorite if the person is already added to the favorite list
- the search result is sorted by a user's name. The header is added based on the first character of the user's name.
- If you click the favorite icon, the user is added to the favorite list. If the user is already in the list, the user is removed from the list. When the favorite icon is clicked, both of the user list and the favorite list should be updated.

### Local favorite user search screen

- you can type a query in the search box, and then search result is shown in the list when you click the search button. Only name field is used when querying in the local database
- the search result is shown in the same way as Github user search screen does
- the search result is sorted by a user's name. The header is added based on the first character of the user's name.
- If you clikc the favorite icon, the user is taken out of the favorite list. Both of the user list and the favorite list should be updated.

## Library and Frameworks

In this app, the following libraries and frameworks are used:

### Android Architecture Components
- ViewModel
- LiveData
- Room (for local database)

### UI
- Google Material Design
- ConstraintLayout
- Sticky Header Layout Manager
- Glide (for image rendering)

### Network
- Retrofit (for API invocation)

### Thread Handling
- Kotlin Coroutines
