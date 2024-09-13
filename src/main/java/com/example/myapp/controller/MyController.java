package com.example.myapp.controller;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
public class MyController {

    @GetMapping("/api/greeting")
    @Operation(
            summary = "Get Greeting",
            description = "Returns a greeting message.",
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "text/plain",
                                    schema = @Schema(type = "string", example = "Hello, World!")
                            )
                    )
            }
    )
    public String getGreeting() {
        return "Hello, World!";
    }

    @PostMapping("/api/submit")
    @Operation(
            summary = "Submit Data",
            description = "Receives data and returns a confirmation message.",
            requestBody = @RequestBody(
                    description = "The data to be submitted",
                    content = @Content(
                            mediaType = "application/json",
                            schema = @Schema(
                                    type = "string",
                                    example = "Sample data")
                    )
            ),
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Data successfully received."),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Bad request, invalid data.")

            }
    )
    public String submitData(@RequestBody String data) {
        return "Data received: " + data;
    }

    @GetMapping("/api/user/{id}")
    @Operation(
            summary = "Get User by ID",
            description = "Fetches a user by their unique ID and returns the user details.",
            parameters = {
                    @Parameter(
                            name = "id",
                            description = "The unique identifier of the user",
                            required = true,
                            example = "123"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful operation",
                            content = @Content(
                                    mediaType = "application/json"
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            description = "User not found"
                    )
            }
    )
    public String getUserById(@PathVariable("id") Long id) {
        return "User ID: " + id;
    }

    @GetMapping("/api/search")
    @Operation(
            summary = "Search Items",
            description = "Searches for items based on a query parameter and returns the matching results.",
            parameters = {
                    @Parameter(
                            name = "query",
                            description = "The search query to find matching items",
                            required = false,
                            example = "example search"
                    )
            },
            responses = {
                    @ApiResponse(
                            responseCode = "200",
                            description = "Successful search operation",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(type = "string", example = "Search results for: example search")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "400",
                            description = "Invalid search query"
                    )
            }
    )
    public String search(
            @RequestParam(name = "query", defaultValue = "")
            @Parameter(description = "Search query") String query) {
        return "Search results for: " + query;
    }
}

