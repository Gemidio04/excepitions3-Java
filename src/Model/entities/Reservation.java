package Model.entities;

import Model.exceptions.DomainException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class Reservation {
    private Integer roomNumber;
    private Date checkIn;
    private Date checkOut;
    private static SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

    // CONSTRUTORES:

    public Reservation(){

    }

    public Reservation(Integer roomNumber, Date checkIn, Date checkOut) {
        // CRIANDO UMA CONDIÇÃO PARA TRATAR UMA EXEÇÃO, PODE EXISTIR:
        if(!checkOut.after(checkIn))
            throw new DomainException("Check-out date must be after check-in date");
        this.roomNumber=roomNumber;
        this.checkIn=checkIn;
        this.checkOut=checkOut;
    }

    // GETTERS AND SETTERS:

    public Integer getRoomNumber(){
        return roomNumber;
    }

    public void setRoomNumber(Integer roomNumber){
        this.roomNumber=roomNumber;
    }

    public Date getCheckIn(){
        return checkIn;
    }

    public Date getCheckOut(){
        return checkOut;
    }

    // MÉTODOS:
    public long duration(){
        long diff = checkOut.getTime() - checkIn.getTime();
        return TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
    }

    public void updateDates(Date checkIn, Date checkOut) {
        Date now = new Date();
        // CONDIÇÕES PARA A COERÊNCIA DAS DATAS DE ENTRADA E SAÍDA DAS RESERVAS:
        if(checkIn.before(now) || checkOut.before(now))
            throw new DomainException("Reservation dates for update must be future dates");
        if(!checkOut.after(checkIn))
            throw new DomainException("Check-out date must be after check-in date");
        this.checkIn=checkIn;
        this.checkOut=checkOut;
    }

    @Override
    public String toString(){
        return "Room "
                + roomNumber
                +  ", check-in: "
                + sdf.format(checkIn)
                + ", check-out: "
                + sdf.format(checkOut)
                + ", "
                + duration()
                + " nights";
    }



}


