package peaksoft.driverapp.dto.driver.mapper;

import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;
import peaksoft.driverapp.dto.driver.DriverRequestDto;
import peaksoft.driverapp.models.entities.AuthInfo;
import peaksoft.driverapp.models.enums.Authority;
import peaksoft.driverapp.models.entities.Driver;

/**
 * @author Beksultan
 */
@Component
public class DriverRegisterRequestMapper implements Converter<DriverRequestDto, Driver> {

    @Override
    public Driver convert(DriverRequestDto dto) {
        Driver driver = new Driver();
        driver.setDriverName(dto.getName());
        driver.setExperience(dto.getExperience());
        driver.setRating(3);

        AuthInfo authInfo = new AuthInfo();
        authInfo.setEmail(dto.getEmail());
        authInfo.setPassword(dto.getPassword());
        authInfo.setAuthority(Authority.DRIVER);

        driver.setAuthInfo(authInfo);

        return driver;
    }
}
