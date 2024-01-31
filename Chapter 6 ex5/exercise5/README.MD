# Project Name

## Overview

Brief description of your project.

## Endpoint Usage

To call the endpoint and provide the `Request-Id` header, follow the steps below:

### Using Postman

1. Open [Postman](https://www.postman.com/) and import your API collection.

2. Locate the request you want to make.

3. Use basic authn in the request: (username and password loaded by data.sql on startup)

    ```
    Username: user
    Password: pass

    ```

    Test with:
    GET localhost:8080/parameter
    GET localhost:8080/ContextHolder 

4. Send the request to the API endpoint.

5. The Respose

    The response body is:
    ```
    Hello, injecting AuthN Obj into parameter:: user!
    ```
    ```
    Hello from Security ContextHolder:: user!
    ```

## Additional Information

Any other relevant information or guidelines can be provided here.

## Contributors

List of contributors or maintainers.

## License

This project is licensed under the [License Name](link-to-license-file).
