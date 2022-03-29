package peaksoft.driverapp.dto.mapper;

/**
 * @author Beksultan
 */
public interface Converter <MODEL, REQUEST, RESPONSE>{

    MODEL convert(REQUEST request);

    RESPONSE deConvert(MODEL model);

}
