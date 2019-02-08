## Viikkoraportti viikolle 4

Tällä viikolla yritin saada javaFX:n toimimaan ohjelmaan, mikä kuitenkin osoittautui Gradlen ja Java 10:n kanssa melko hankalaksi.
Lopulta päädyin käyttämään JavaFX:n sijaan ilman packageja tai importteja toimivaa Javan Swingiä ja toteutin tällä melko
yksinkertaisen graafisen käyttöliittymän, joka ottaa käyttäjältä syötteet ja piirtää niiden perusteella kuvan ohjelman ikkunaan.
Lisäksi hyvä lohko viikon ajasta kului alustavaan dokumentaation kirjoittamiseen. 
Tällä viikolla tein projektin eteen töitä noin 10 tuntia.

Tällä viikolla opin siis lähinnä Javan Swingin käyttöä. Lisäksi selvisi varmuudella melko kiinnostamaton tulos, että molemmat
implementoidut algoritmit ovat kaksiulotteisilla pisteillä aika- ja tilavaativuusluokkaa O(1).

Eräs epäselväksi jäänyt kohta on Steve Worleyn artikkelin mainitsemat Worley noise -algoritmin optimoinnit, joiden toteutus jää
artikkelissa hieman varjoon.

Seuraavaksi teen luultavasti jotain yhdistelmää algoritmien implementaatiosta kolmanteen ulottuvuuteen, dokumentaatiosta ja 
testauksesta, ja käyttöliittymän parantelemisesta.

---

Jätän varmaankin gradle-tiedostot repositorioon ainakin toistaiseksi, ja kenties poistan ne reposta ennen kurssin loppupalautusta.
