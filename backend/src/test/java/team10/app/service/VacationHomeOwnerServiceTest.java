//package team10.app.service;
//
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.ArgumentCaptor;
//import org.mockito.Mock;
//import org.mockito.Mockito;
//import org.mockito.junit.jupiter.MockitoExtension;
//import team10.app.dto.AddressDto;
//import team10.app.dto.VacationHomeDto;
//import team10.app.model.Address;
//import team10.app.model.Picture;
//import team10.app.model.VacationHome;
//import team10.app.model.VacationHomeOwner;
//import team10.app.repository.AddressRepository;
//import team10.app.repository.PictureRepository;
//import team10.app.repository.VacationHomeOwnerRepository;
//import team10.app.repository.VacationHomeRepository;
//import team10.app.util.Validator;
//
//import java.nio.charset.StandardCharsets;
//import java.util.*;
//
//import static org.assertj.core.api.Assertions.assertThatThrownBy;
//import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
//import static org.mockito.BDDMockito.given;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//class VacationHomeOwnerServiceTest {
//
//    @Mock
//    private VacationHomeOwnerRepository vacationHomeOwnerRepository;
//    @Mock
//    private VacationHomeRepository vacationHomeRepository;
//    @Mock
//    private AddressRepository addressRepository;
//    @Mock
//    private PictureService pictureService;
//    @Mock
//    private PictureRepository pictureRepository;
//    @Mock
//    private Validator validator;
//    private VacationHomeOwnerService vacationHomeOwnerService;
//
//    @BeforeEach
//    void setUp() {
//        vacationHomeOwnerService = new VacationHomeOwnerService(
//                vacationHomeOwnerRepository,
//                vacationHomeRepository,
//                addressRepository,
//                pictureService,
//                pictureRepository,
//                validator);
//    }
//
//    @Test
//    void shouldAddVacationHome() {
////        // given
////        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
////                "Stan na dan",
////                new AddressDto("Ulica b.b.", "Grad", "Drzava"),
////                "Stan na dan za jedan dan stan",
////                "Ponasalje mora biti lijepo",
////                "Svasta nesto nudimo",
////                12,
////                Arrays.asList("12", "2"),
////                10,
////                20
////        );
////        VacationHomeOwner vacationHomeOwner = new VacationHomeOwner();
////        VacationHome vacationHome = vacationHomeOwnerService.buildVacationHome(vacationHomeDTO);
////        when(validator.validateVacationHomeDTO(Mockito.any(VacationHomeDto.class))).thenReturn(true);
////        when(vacationHomeOwnerRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(vacationHomeOwner));
////        // when
////        vacationHomeOwnerService.addVacationHome(vacationHomeDTO, "test@gmail.com");
////        // then
////        ArgumentCaptor<VacationHome> argumentCaptor = ArgumentCaptor.forClass(VacationHome.class);
////        verify(vacationHomeRepository).save(argumentCaptor.capture());
////        VacationHome capturedVacationHome = argumentCaptor.getValue();
////        assertThat(capturedVacationHome).isEqualTo(vacationHome);
//    }
//
//    @Test
//    void shouldNotAddVacationHomeAndShouldThrowRuntimeException() {
//        // given
//        AddressDto addressDto = new AddressDto("Ulica b.b.", "Grad", "Drzava");
//        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
//                "Stan na dan",
//                addressDto,
//                "Stan na dan za jedan dan stan",
//                "Ponasalje mora biti lijepo",
//                "Svasta nesto nudimo",
//                12,
//                Arrays.asList("12", "2"),
//                10,
//                20
//        );
//        //given
//        given(validator.validateVacationHomeDTO(Mockito.any(VacationHomeDto.class))).willReturn(false);
//        // when
//        //then
//        assertThatThrownBy(() -> vacationHomeOwnerService.addVacationHome(vacationHomeDTO, "test@gmail.com")).isInstanceOf(RuntimeException.class);
//
//        verify(vacationHomeRepository, never()).save(any());
//    }
//
//    @Test
//    void shouldNotAddVacationHome() {
////        // given
////        Address address = new Address("Ulica b.b.", "Grad", "Drzava");
////        AddressDto addressDto = new AddressDto("Ulica b.b.", "Grad", "Drzava");
////
////        addressRepository.save(address);
////        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
////                "Stan na dan",
////                addressDto,
////                "Stan na dan za jedan dan stan",
////                "Ponasalje mora biti lijepo",
////                "Svasta nesto nudimo",
////                12,
////                Arrays.asList("12", "2"),
////                10,
////                20
////        );
////        VacationHome vacationHome = vacationHomeOwnerService.buildVacationHome(vacationHomeDTO);
////        when(validator.validateVacationHomeDTO(Mockito.any(VacationHomeDto.class))).thenReturn(true);
////        VacationHomeOwner vacationHomeOwner = new VacationHomeOwner();
////        when(vacationHomeOwnerRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(vacationHomeOwner));
////        // when
////        vacationHomeOwnerService.addVacationHome(vacationHomeDTO, "test@gmail.com");
////        //then
//
//    }
//
//    @Test
//    void saveVacationHome() {
////        VacationHomeDto vacationHomeDTO = new VacationHomeDto(
////                "Stan na dan",
////                new AddressDto("Ulica b.b.", "Grad", "Drzava"),
////                "Stan na dan za jedan dan stan",
////                "Ponasalje mora biti lijepo",
////                "Svasta nesto nudimo",
////                12,
////                Arrays.asList("12", "2"),
////                10,
////                20
////        );
////        VacationHome vacationHome = vacationHomeOwnerService.buildVacationHome(vacationHomeDTO);
////        vacationHomeRepository.save(vacationHome);
////        verify(vacationHomeRepository).save(vacationHome);
//    }
//
//    @Test
//    void getAllActiveVacationHomesByOwnerEmail_returnsNonEmptyList() {
//        // given
//        VacationHomeOwner vacationHomeOwner = new VacationHomeOwner();
//        Picture pictureCompressed = new Picture("jpeg",
//                PictureService.compressBytes("123asdasdasdasdasdasddasdasdaa".getBytes(StandardCharsets.UTF_8)));
//        Set<Picture> pictureSet = new HashSet<>(List.of(pictureCompressed));
//        VacationHome vacationHome = new VacationHome();
//        vacationHome.setTitle("Some title");
//        vacationHome.setAddress(new Address("Strt", "Cityyy", "Westeros"));
//        vacationHome.setDescription("Some long description");
//        vacationHome.setRulesOfConduct("All is allowed");
//        vacationHome.setAdditionalServices("24/7 massage");
//        vacationHome.setPrice(1000);
//        vacationHome.setPictures(pictureSet);
//        vacationHome.setRooms(3);
//        vacationHome.setBeds(3);
//        vacationHomeOwner.setVacationHomes(new HashSet<>(Collections.singleton(vacationHome)));
//        vacationHome.setOwner(vacationHomeOwner);
//
//        when(vacationHomeOwnerRepository.findByEmail(Mockito.anyString())).thenReturn(Optional.of(vacationHomeOwner));
//        when(vacationHomeRepository.findAllByOwner(Mockito.any())).thenReturn(List.of(vacationHome));
//
//        Set<VacationHomeDto> vacationHomeDtos = vacationHomeOwnerService.getAllActiveVacationHomesByOwnerEmail("test@gmail.com");
//        assertThat(vacationHomeDtos.isEmpty()).isFalse();
//    }
//
//
//}