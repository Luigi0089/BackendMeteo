# Test Finale Backend – Sistemi Informativi S.p.A.

Questo progetto rappresenta il **backend sviluppato come test finale** dell’Academy organizzata da **Sistemi Informativi S.p.A.**  
Il backend espone API REST che consentono la gestione degli utenti, delle località e delle previsioni meteo, integrandosi con servizi esterni.

---

## 🧩 Funzionalità principali

- Registrazione di nuovi utenti
- Login e gestione base delle sessioni
- Ricerca di località
- Salvataggio delle località preferite per ciascun utente
- Recupero delle previsioni meteo tramite API esterne

---

## ⚙️ Tecnologie utilizzate

- Java 17+
- Spring Boot
- Spring Web
- Spring Data JPA
- H2 / PostgreSQL / MySQL 
- API esterne per meteo 

---

## ▶️ Avvio del progetto

**Clona il repository**
   ```bash
   git clone https://github.com/Luigi0089/BackendMeteo.git
   cd backend
```
  
### ⚙️ Configura il file `application.properties` o `application.yml`
Inserire:

- URL e credenziali del database


## 🌐 Accedi al backend
L’app sarà raggiungibile su:
   ```bash
http://localhost:8080/
```

## 📘 API disponibili
Le API sono esposte in formato REST.

## 🔗 Collegamento con il frontend
Il frontend Angular comunica con questo backend per:

- Effettuare login e registrazione
- Cercare e salvare località preferite
- Ottenere i dati meteo

> Assicurarsi che il backend sia in esecuzione e accessibile dal frontend (con configurazione CORS se necessaria).


### 👤 Autore

Realizzato da **Luigi Vasile**  
Nell’ambito del test finale dell’Academy di **Sistemi Informativi S.p.A.**
