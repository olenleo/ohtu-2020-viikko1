package ohtu.ohtuvarasto;

import org.junit.*;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class VarastoTest {

    Varasto varasto, taysiVarasto, puoliTaysiVarasto,negatiivinenVarasto, toinenNegatiivinenVarasto;
    double vertailuTarkkuus = 0.0001;

    @Before
    public void setUp() {
        varasto = new Varasto(10);
        negatiivinenVarasto= new Varasto(-0.1, -0.1);
        toinenNegatiivinenVarasto = new Varasto(-20);
        taysiVarasto = new Varasto(10,20);
        puoliTaysiVarasto = new Varasto(10,5);
    }
    @Test
    public void negatiivinenLisaysEiMuutaSaldoa() {
        varasto.lisaaVarastoon(-20);
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void negatiivinenKonstruktoriToimiiOikein() {
        assertEquals(0.2, toinenNegatiivinenVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void yliTilavuusKorjataan() {
        assertEquals(10, taysiVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void yliTilavuusLuoTaydenVaraston() {
        assertEquals(10, taysiVarasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void negatiivinenTilavuusLuoKayttokelvottomanVaraston() {
        assertEquals(0.0, negatiivinenVarasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void alustettuunVarastoonMahtuu() {
        assertEquals(5, puoliTaysiVarasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void konstruktoriLuoTyhjanVaraston() {
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }

    @Test
    public void uudellaVarastollaOikeaTilavuus() {
        assertEquals(10, varasto.getTilavuus(), vertailuTarkkuus);
    }

    @Test
    public void lisaysLisaaSaldoa() {
        varasto.lisaaVarastoon(8);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(8, varasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void negatiivinenLisaysEiLisaaSaldoa() {
        varasto.lisaaVarastoon(1);
        varasto.lisaaVarastoon(-0.1);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void negatiivinenLisaysEiLisaaSaldoaTyhjaanVarastoon() {
        
        varasto.lisaaVarastoon(-0.1);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(0, varasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void ylikuormaPoistuuKunLisaaSaldoa() {
        varasto.lisaaVarastoon(18);

        // saldon pitäisi olla sama kun lisätty määrä
        assertEquals(10, varasto.getSaldo(), vertailuTarkkuus);
    }

    

    @Test
    public void lisaysLisaaPienentaaVapaataTilaa() {
        varasto.lisaaVarastoon(8);

        // vapaata tilaa pitäisi vielä olla tilavuus-lisättävä määrä eli 2
        assertEquals(2, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void ottaminenPalauttaaOikeanMaaran() {
        varasto.lisaaVarastoon(8);

        double saatuMaara = varasto.otaVarastosta(2);

        assertEquals(2, saatuMaara, vertailuTarkkuus);
    }

    @Test
    public void NegatiivinenOtaaminenEiVahennaSaldoa() {
        varasto.lisaaVarastoon(1);
        varasto.otaVarastosta(-1);
        // saldon ei pitäisi muuttua
        assertEquals(1, varasto.getSaldo(), vertailuTarkkuus);
    }
    @Test
    public void otetaanKaikkiMitaVoidaan() {
        puoliTaysiVarasto.otaVarastosta(20);
        // saldon pitäisi olla 0
        assertEquals(0.0, varasto.getSaldo(), vertailuTarkkuus);
    }


    @Test
    public void ottaminenLisääTilaa() {
        varasto.lisaaVarastoon(8);

        varasto.otaVarastosta(2);

        // varastossa pitäisi olla tilaa 10 - 8 + 2 eli 4
        assertEquals(4, varasto.paljonkoMahtuu(), vertailuTarkkuus);
    }

    @Test
    public void toStringMetodiPalauttaaOikein() {
        String vertailtava = "saldo = " + 5.0 + ", vielä tilaa " + 5.0;
        assertEquals(vertailtava, puoliTaysiVarasto.toString());
        
    }

}