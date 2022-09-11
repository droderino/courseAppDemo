# courseAppDemo

````json
{
  "openapi": "3.0.1",
  "info": {
    "title": "OpenAPI definition",
    "version": "v0"
  },
  "servers": [
    {
      "url": "http://localhost:8080",
      "description": "Generated server url"
    }
  ],
  "paths": {
    "/course": {
      "get": {
        "tags": [
          "course-controller"
        ],
        "summary": "Übersicht über alle angebotenen Schulungen und anzeigen von Schulung für einen bestimmten Zeitraum",
        "operationId": "findCources",
        "parameters": [
          {
            "name": "von",
            "in": "query",
            "required": false,
            "schema": {
              "type": "string",
              "format": "date-time"
            }
          },
          {
            "name": "bis",
            "in": "query",
            "required": false,
            "schema": {
              "type": "string",
              "format": "date-time"
            }
          },
          {
            "name": "pageable",
            "in": "query",
            "required": true,
            "schema": {
              "$ref": "#/components/schemas/Pageable"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "application/hal+json": {
                "schema": {
                  "$ref": "#/components/schemas/PagedModelCourseDTO"
                }
              }
            }
          }
        }
      },
      "put": {
        "tags": [
          "course-controller"
        ],
        "summary": "Updaten einer existierenden Schulung",
        "operationId": "update",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/Course"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CourseDTO"
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "course-controller"
        ],
        "summary": "Hinzufügen einer neuen Schulung",
        "operationId": "insert",
        "requestBody": {
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/Course"
              }
            }
          },
          "required": true
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CourseDTO"
                }
              }
            }
          }
        }
      }
    },
    "/course/{id}/booking": {
      "get": {
        "tags": [
          "booking-controller"
        ],
        "summary": "Übersicht aller Buchungen für eine Schulung",
        "operationId": "findCourses",
        "parameters": [
          {
            "name": "courseId",
            "in": "path",
            "description": "Course ID",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "application/hal+json": {
                "schema": {
                  "$ref": "#/components/schemas/PagedModelBookingDTO"
                }
              }
            }
          }
        }
      },
      "post": {
        "tags": [
          "booking-controller"
        ],
        "summary": "Buchen einer bestimmten Schulung",
        "operationId": "insert_1",
        "parameters": [
          {
            "name": "courseId",
            "in": "path",
            "description": "Course ID",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "requestBody": {
          "description": "Details der neuen Buchung",
          "content": {
            "application/json": {
              "schema": {
                "$ref": "#/components/schemas/BookingDTO"
              }
            }
          }
        },
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/BookingDTO"
                }
              }
            }
          }
        }
      }
    },
    "/course/{id}": {
      "get": {
        "tags": [
          "course-controller"
        ],
        "summary": "Abfragen einer Schulung",
        "operationId": "findById",
        "parameters": [
          {
            "name": "id",
            "in": "path",
            "description": "Course ID",
            "required": true,
            "schema": {
              "type": "integer",
              "format": "int32"
            }
          }
        ],
        "responses": {
          "200": {
            "description": "OK",
            "content": {
              "*/*": {
                "schema": {
                  "$ref": "#/components/schemas/CourseDTO"
                }
              }
            }
          }
        }
      }
    }
  },
  "components": {
    "schemas": {
      "Course": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int32"
          },
          "name": {
            "type": "string"
          },
          "description": {
            "type": "string"
          },
          "lecturer": {
            "type": "string"
          },
          "price": {
            "type": "number"
          },
          "periodStart": {
            "type": "string",
            "format": "date-time"
          },
          "periodEnd": {
            "type": "string",
            "format": "date-time"
          }
        }
      },
      "CourseDTO": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int32"
          },
          "name": {
            "type": "string"
          },
          "description": {
            "type": "string"
          },
          "lecturer": {
            "type": "string"
          },
          "price": {
            "type": "number"
          },
          "begin": {
            "type": "string",
            "format": "date-time"
          },
          "end": {
            "type": "string",
            "format": "date-time"
          },
          "links": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/Link"
            }
          }
        }
      },
      "Link": {
        "type": "object",
        "properties": {
          "rel": {
            "type": "string"
          },
          "href": {
            "type": "string"
          },
          "hreflang": {
            "type": "string"
          },
          "media": {
            "type": "string"
          },
          "title": {
            "type": "string"
          },
          "type": {
            "type": "string"
          },
          "deprecation": {
            "type": "string"
          },
          "profile": {
            "type": "string"
          },
          "name": {
            "type": "string"
          }
        }
      },
      "BookingDTO": {
        "type": "object",
        "properties": {
          "id": {
            "type": "integer",
            "format": "int32"
          },
          "customer": {
            "type": "string"
          },
          "course": {
            "$ref": "#/components/schemas/Course"
          }
        }
      },
      "Pageable": {
        "type": "object",
        "properties": {
          "page": {
            "minimum": 0,
            "type": "integer",
            "format": "int32"
          },
          "size": {
            "minimum": 1,
            "type": "integer",
            "format": "int32"
          },
          "sort": {
            "type": "array",
            "items": {
              "type": "string"
            }
          }
        }
      },
      "PageMetadata": {
        "type": "object",
        "properties": {
          "size": {
            "type": "integer",
            "format": "int64"
          },
          "totalElements": {
            "type": "integer",
            "format": "int64"
          },
          "totalPages": {
            "type": "integer",
            "format": "int64"
          },
          "number": {
            "type": "integer",
            "format": "int64"
          }
        }
      },
      "PagedModelCourseDTO": {
        "type": "object",
        "properties": {
          "links": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/Link"
            }
          },
          "content": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/CourseDTO"
            }
          },
          "page": {
            "$ref": "#/components/schemas/PageMetadata"
          }
        }
      },
      "PagedModelBookingDTO": {
        "type": "object",
        "properties": {
          "links": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/Link"
            }
          },
          "content": {
            "type": "array",
            "items": {
              "$ref": "#/components/schemas/BookingDTO"
            }
          },
          "page": {
            "$ref": "#/components/schemas/PageMetadata"
          }
        }
      }
    }
  }
}
````