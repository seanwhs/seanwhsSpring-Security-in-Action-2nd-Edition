# Project Name

## Overview

Brief description of your project.

## Endpoint Usage

To call the endpoint and provide the `Request-Id` header, follow the steps below:

### Using Postman

1. Open [Postman](https://www.postman.com/) and import your API collection.

2. Locate the request you want to make.

3. Add the following header element in the request:

    ```
    Request-Id: 12345

    ```

4. Send the request to the API endpoint.

5. The Respose

    The response body is:
    ```
    Hello
    ```

    In the console, you can see a line similar to this:
    ```
    INFO 18448 --- [nio-8080-exec-1] c.e.service.AuthenticationLoggingFilter:: Successfully authenticated request with id 12345
    ```

## Additional Information

Any other relevant information or guidelines can be provided here.

## Contributors

List of contributors or maintainers.

## License

This project is licensed under the [License Name](link-to-license-file).
