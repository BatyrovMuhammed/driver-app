package peaksoft.driverapp.dto;

/**
 * @author B.Muhammed
 */
public interface Converter <MODEL, REQUEST, RESPONSE>{

    MODEL convert(REQUEST request);

    RESPONSE deConvert(MODEL model);

}
