package Negocio;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;
import java.util.Locale;

public class prueba {

    public static void main(String[] args) {

        String numero = "hola";
        
        String imprimir = String.format("Hola :%-4S: pero", numero);
        System.out.println(imprimir);
        
    }

}
