package ar.com.ada.api.aladas;

import static org.junit.jupiter.api.Assertions.*;

import java.math.BigDecimal;
//import java.util.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import ar.com.ada.api.aladas.entities.Aeropuerto;
import ar.com.ada.api.aladas.entities.Usuario;
import ar.com.ada.api.aladas.entities.Vuelo;
import ar.com.ada.api.aladas.entities.Vuelo.EstadoVueloEnum;
import ar.com.ada.api.aladas.security.Crypto;
import ar.com.ada.api.aladas.services.AeropuertoService;
import ar.com.ada.api.aladas.services.VueloService;
import ar.com.ada.api.aladas.services.VueloService.ValidacionVueloDataEnum;

@SpringBootTest
class AladasApplicationTests {

	@Autowired
	VueloService vueloService;

	@Autowired
	AeropuertoService aeropuertoService;

	@Test
	void vueloTestPrecioNegativo() {

		Vuelo vueloConPrecioNegativo = new Vuelo();
		vueloConPrecioNegativo.setPrecio(new BigDecimal(-100));
		assertFalse(vueloService.validarPrecio(vueloConPrecioNegativo));

	}

	@Test
	void vueloTestPrecioOk() {

		Vuelo vueloConPrecioOK = new Vuelo();
		vueloConPrecioOK.setPrecio(new BigDecimal(100));
		assertTrue(vueloService.validarPrecio(vueloConPrecioOK));

	}

	@Test
	void aeropuertoValidarCodigoIATAOK() {

		String codigoIATAOk1 = "EZE";
		String codigoIATAOk2 = "AEP";
		String codigoIATAOk3 = "NQN";
		String codigoIATAOk4 = "N  ";


		Aeropuerto aeropuerto1 = new Aeropuerto();
		aeropuerto1.setCodigoIATA(codigoIATAOk1);

		Aeropuerto aeropuerto2 = new Aeropuerto();
		aeropuerto2.setCodigoIATA(codigoIATAOk2);

		Aeropuerto aeropuerto3= new Aeropuerto();
		aeropuerto3.setCodigoIATA(codigoIATAOk3);

		
		Aeropuerto aeropuerto4= new Aeropuerto();
		aeropuerto4.setCodigoIATA(codigoIATAOk4);

		assertTrue(aeropuertoService.validarCodigoIATA(aeropuerto1));
		assertTrue(aeropuertoService.validarCodigoIATA(aeropuerto2));
		assertTrue(aeropuertoService.validarCodigoIATA(aeropuerto3));


		assertFalse(aeropuertoService.validarCodigoIATA(aeropuerto4));

		
	}

	@Test
	void aeropuertoValidarCodigoIATANoOK() {
		// el c??digo no debe llevar n??mero y s??lo 3 letras, as?? que habr??a que limitarlo
		// a eso, no?

	}

	@Test
	void vueloVerificarValidacionAeropuertoOrigenDestino() {
		// En este validar todas las posibilidades de si el aeropuerto
		// origen es igual al de destion o todo lo que se les ocurra
	}

	//@Test
	//void vueloChequearQueLosPendientesNoTenganVuelosViejos(DateTime currentTime)) {

		//DateTime currentTime;
		//for each vuelo in lista de vuelos:
		//assert vuelo.getfecha 

		// Queremos validar que cuando hagamos un metodo que traiga los vuelos actuales
		// para
		// hacer reservas, no haya ningun vuelo en el pasado.
	//}

	@Test
	void vueloVerificarCapacidadMinima() {

	}

	@Test
	void vueloVerificarCapadidadMaxima() {

	}

	@Test
	void aeropuertoTestBuscadorIATA() {

	}

	@Test
	void vueloValidarVueloMismoDestionoUsandoGeneral() {
		Vuelo vuelo = new Vuelo();
		vuelo.setPrecio(new BigDecimal(1000));
		vuelo.setEstadoVueloId(EstadoVueloEnum.GENERADO);
		vuelo.setAeropuertoOrigen(116);
		vuelo.setAeropuertoDestino(116);

		assertEquals( ValidacionVueloDataEnum.ERROR_AEROPUERTOS_IGUALES, vueloService.validar(vuelo));
	}

	@Test
	void testearEncriptacion() {

		String contrase??aImaginaria = "pitufosasesinos";
		String contrase??aImaginariaEncriptada = Crypto.encrypt(contrase??aImaginaria, "palabra");

		String contrase??aImaginariaEncriptadaDesencriptada = Crypto.decrypt(contrase??aImaginariaEncriptada, "palabra");

		// assertTrue(contrase??aImaginariaEncriptadaDesencriptada.equals(contrase??aImaginaria));
		assertEquals(contrase??aImaginariaEncriptadaDesencriptada, contrase??aImaginaria);
	}

	@Test
	void testearContrase??a() {
		Usuario usuario = new Usuario();

		usuario.setUsername("Diana@gmail.com");
		usuario.setPassword("qp5TPhgUtIf7RDylefkIbw==");
		usuario.setEmail("Diana@gmail.com");

		assertFalse(!usuario.getPassword().equals(Crypto.encrypt("AbcdE23", usuario.getUsername().toLowerCase())));

	}

}


