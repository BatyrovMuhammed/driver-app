package peaksoft.driverapp.dto;

/**
 * @author Beksultan
 */
public interface Converter <MODEL, REQUEST, RESPONSE>{

    MODEL convert(REQUEST request);

    RESPONSE deConvert(MODEL model);

}
