package com.goodhope.abelguevaratest.payload;

/**
 * Clase genérica para encapsular una respuesta de la API.
 * <p>
 * Esta clase contiene un código de estado, un mensaje descriptivo y los datos de la respuesta.
 * Se utiliza para estandarizar las respuestas en las llamadas a la API.
 * </p>
 *
 * @param <T> El tipo de datos que se incluirá en la respuesta.
 */
public class ApiResponse<T> {
    private final int status;
    private final String message;
    private final T data;

    public ApiResponse(int status, String message, T data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }

    public int getStatus() {
        return status;
    }
    public String getMessage() {
        return message;
    }
    public T getData() {
        return data;
    }
}
