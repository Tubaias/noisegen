## Viikkoraportti viikolle 2

Tällä viikolla otin loin itse projektin gradlella ja otin käyttöön JUnitin, Jacocon ja Javadocsin.
Projektin alustamisen jälkeen kirjoitin yksinkertaisen rungon ohjelmalle, melkolailla valmiin Perlin noise -implementaation ja
apuluokat joiden avulla melualgoritmilla voidaan luoda taulukoita, jotka voidaan sitten tulostaa komentoriville ASCII-kuvana.
Uskoisin siis ohjelman edistyvän hyvällä vauhdilla. Viikolla tein projektin eteen töitä yhteensä noin 12 tuntia.

Algoritmin toteutus vaati perinpohjaista perehtymistä Perlin noise -algoritmin toimintaan, ja vaikkakin jotkin toiminnan seikat
ovat hieman epäselviä, ymmärrän sen toiminnan suurimmaksi osin. Myöskin gradlen käyttö sai hyvää kertausta.

Tähän mennessä tehdyissä asioissa ei ole ollut suurempia epäselvyyksiä, mutta tulevia ominaisuuksia varten täytyy tehdä vielä
paljon tutkimusta. Pian täytyy ainakin selvittää Worley noise -algoritmin toimintaperiaate ja löytää sopiva Java-kirjasto
2D- ja kenties myös 3D-kuvien piirtämiseen. Tällä hetkellä harkitsen joko JavaFX:n tai LWJGL:n käyttöä.

Ensi viikolla yritän toteuttaa Worley noisen, lisätä ainakin jotain käyttäjäsyötteitä ohjelman toimintaan, ja jos aika riittää, kenties
ottaa käyttöön jonkin graafiikkakirjaston.

---

Vastauksena viikon 1 palautteessa olleeseen kysymykseen algoritmien 'hyvyyden' evaluoinnista:

Melualgorimien hyvyyden arviointi on hieman haastavaa, koska algoritmien käyttötarkoitus on juuri visuaalisissa sovelluksissa,
kuten 3D-grafiikassa, maastongeneroinnissa ja textuurien generoinnissa. Tämän vuoksi melualgoritmin merkittävät ominaisuudet ovat
juuri sen aikavaativuus sekä sen tuottama visuaalinen lopputulos. Yritän kuitenkin keksiä projektiin jotain ylimääräistä
metriikkaa algoritmeistä, kuten kenties algoritmien tuottamien arvojen muodostamien jakaumien vertailu.
