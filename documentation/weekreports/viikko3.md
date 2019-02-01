## Viikkoraportti viikolle 3

Tällä viikolla toteutin Worley noise -algoritmin, generoitujen tulosten piirtämisen png-tiedostoon ja generoimisen ohjaamisen
komentorivillä annettavilla käyttäjäsyötteillä. Jos en päädy toteuttamaan ylimääräisiä algoritmeja, ohjelman perustoiminta on nyt
kasassa. Käytin viikolla projektin eteen noin 10 tuntia.

Uutta opittua asiaa viikolla oli varsinkin Worley noise -algoritmin toteutus ja samankaltaiset algoritmit, kuten Voronoin algoritmi.
Lisäksi opin kuvien piirtämisestä tiedostoon javan standardikirjaston luokkien ImageIO ja BufferedImage avulla. 
Jo toteutetuissa asioissa ei taaskaan ole pahemmin epäselvyyksiä, mutta tulevia ominaisuuksia varten pitää opetella vielä monia asioita.

Seuraavaksi yritän joko tutkia algoritmin soveltamista kolmanteen ulottuvuuteen ja 3d-piirtämistä, tai lisään ohjelmaan statistiikkaa
ja enemmän käyttöliittymätoimintoja. Worley noisen toteutuksessa oleva Math.sqrt pitää myös vielä korvata omalla toteutuksella.

---

Pitäisin henkilökohtaisesti gradle-tiedostot projektin repositoriossa, jotta kuka tahansa voi ladata projektin ja ajaa sen suoraan,
ilman että gradle täytyy asentaa erikseen. Mitä näiden tiedostojen säilyttäminen voi rikkoa? Hyvällä syyllä poistan ne toki.
