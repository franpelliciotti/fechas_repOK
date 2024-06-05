
/**
 * Fecha: clase cuyos objetos representan fechas calendarias, para el 
 * calendario gregoriano. Esta clase implementa una variedad de 
 * funcionalidades sobre fechas, como comparaciones, cómputos de días,
 * etc.
 * 
 * @author N. Aguirre 
 * @version 0.1
 */
public class Fecha
{
    // dia de la fecha
    private int dia;
    
    // mes de la fecha
    private int mes;
    
    // año de la fecha
    private int anho;

    /**
     * Constructor por defecto, para la clase fecha.
     * Crea como fecha por defecto la fecha inicial del 
     * calendario gregoriano (15/10/1582)
     */
    public Fecha()
    {
        dia = 15;
        mes = 10;
        anho = 1582;
        assert repOK(): "No se preserva el invariante de clase.";
    }

    /**
     * Constructor de la clase Fecha, que recibe la fecha a 
     * crear como parámetro. La fecha no puede ser previa
     * al 15/10/1582. Debe tenerse en cuenta la creación de
     * fechas válidas, en relación a número de días en los
     * meses, años bisiestos, etc.
     * Precondición: Que la fecha sea válida.
     * Postcondición: La fecha se crea.
     */
    public Fecha(int nuevoDia, int nuevoMes, int nuevoAnho)
    {
        if (!(diaValido(nuevoDia, nuevoMes, nuevoAnho))) throw new IllegalArgumentException("La fecha no es válida.");
        dia = nuevoDia;
        mes = nuevoMes;
        anho = nuevoAnho;
        assert repOK(): "No se preserva el invariante de clase.";
    }
    
    public String toString(Fecha fecha){
        return fecha.obtenerDia() + "/" + fecha.obtenerMes() + "/" + fecha.obtenerAnho();
    }
    
    /**
     * Retorna el dia de la fecha
     */
    public int obtenerDia() {
        return dia;
    }
    
    /**
     * Retorna el mes de la fecha
     */
    public int obtenerMes() {
        return mes;
    }
    
    /**
     * Retorna el año de la fecha
     */
    public int obtenerAnho() {
        return anho;
    }
    
    /**
     * Cambia el día de la fecha. El nuevo día debe ser válido
     * para el mes y año actuales.
     * Precondición: Que el día sea válido según la fecha.
    */
    public void cambiarDia(int nuevoDia, int nuevoMes, int nuevoAnho) {
        assert(diaValido(nuevoDia, nuevoMes, nuevoAnho));
        nuevoDia = dia;
        assert repOK(): "No se preserva el invariante de clase.";
    }
    
    /**
     * Cambia el mes de la fecha. El nuevo mes debe ser válido
     * para el día y año actuales.
     * Precondición: Que el mes sea válido según la fecha.
     */
    public void cambiarMes(int nuevoDia, int nuevoMes, int nuevoAnho) {
        assert(diaValido(nuevoDia, nuevoMes, nuevoAnho));
        nuevoMes = mes;
        assert repOK(): "No se preserva el invariante de clase.";
    }
    
    /**
     * Cambia el año de la fecha. El nuevo año debe ser válido
     * para el día y mes actuales.
     * Precondición: Que el año sea válido según la fecha.
     */
    public void cambiarAnho(int nuevoDia, int nuevoMes, int nuevoAnho) {
        assert(diaValido(nuevoDia, nuevoMes, nuevoAnho));
        nuevoAnho = anho;
        assert repOK(): "No se preserva el invariante de clase.";
    }
    
    /**
     * Chequea si la fecha es igual a otra fecha dada
     */
    public boolean equals(Fecha otraFecha) {
        if (dia == otraFecha.obtenerDia() && mes == otraFecha.obtenerMes() && anho == otraFecha.obtenerAnho()){
            return true;
        }
        else {
            return false;
        }
    }
    
    /**
     * Chequea si la fecha es anterior a otra fecha dada
     */
    public boolean esAnterior(Fecha otraFecha) {
        if (equals(otraFecha)){
            return false;
        }
        if (anho > otraFecha.obtenerAnho()){
            return false;
        }
        else{
            if (mes > otraFecha.obtenerMes()){
                return false;
            }
            else if (dia > otraFecha.obtenerDia()){
                return false;
            }
            else{
                return true;
            }
        }
    }
    
    /**
     * Computa distancia en días entre dos fechas.
     * El parámetro otraFecha debe corresponder a una fecha igual o 
     * posterior a la fecha del objeto.
     * Precondición: La fecha del parámetro debe ser mayor a la del objeto.
     */
    public int cantidad(Fecha otraFecha) {
        if(!(esAnterior(otraFecha))) throw new IllegalStateException("La fecha del parámetro debe ser mayor a la del objeto.");
        int anhoActual = anho;
        //Conversiones a días:
        int fechaMesEnDias = 0;
        int fechaAnhoEnDias = 0;
        int otraFechaMesEnDias = 0;
        int otraFechaAnhoEnDias = 0;
        // Conversión a días de la fecha actual:
        for(int i = 1582; i < anhoActual; i++)
            if(esBisiesto(anho)) {
                fechaAnhoEnDias += 366;
            }
            else {
                fechaAnhoEnDias += 365;
            }
        if(mes == 2){
            if(esBisiesto(anho)){
                fechaMesEnDias = 29;
            }
            else{
                fechaMesEnDias = 28;
            }
        }
        else if(mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12){
            fechaMesEnDias = 31;
        }
        else {
            fechaMesEnDias = 30;
        }
        
        //Conversión a días de la fecha parámetro (Fecha otraFecha):
        if(esBisiesto(otraFecha.obtenerAnho())) {
            otraFechaAnhoEnDias = anho * 366;
        }
        else {
            otraFechaAnhoEnDias = anho * 365;
        }
        
        if(otraFecha.obtenerMes() == 2){
            if(esBisiesto(otraFecha.obtenerAnho())){
                otraFechaMesEnDias = 29;
            }
            else{
                otraFechaMesEnDias = 28;
            }
        }
        else if(otraFecha.obtenerMes() == 1 || otraFecha.obtenerMes() == 3 || otraFecha.obtenerMes() == 5 || otraFecha.obtenerMes() == 7 || otraFecha.obtenerMes() == 8 || otraFecha.obtenerMes() == 10 || otraFecha.obtenerMes() == 12){
            otraFechaMesEnDias = 31;
        }
        else {
            otraFechaMesEnDias = 30;
        }
         
       //Cálculo de días en total:
        int restaDias = otraFecha.obtenerDia() - dia;
        int restaMeses = otraFecha.obtenerMes() - mes;
        int restaAnhos = otraFecha.obtenerAnho() - anho;
        int diasTotal = restaDias + restaMeses + restaAnhos;
        
        int i = 0;
        //Acumulador de días:
        while(esAnterior(otraFecha) && i >= 0){
            if(restaDias < 1){
                restaMeses += 1;
            }
            
            else if(restaMeses < 1){
                restaAnhos += 1;
            }
            i ++;
        }
        return diasTotal;
    }
    
    /**
     * Computa la cantidad de días de un mes dado. El parámetro
     * debe corresponder a un mes válido.
     */
    private int cantidadDias(int unMes, int unAnho) 
    {
        // Implementar este método, reemplazando la línea siguiente
        assert(unMes >= 1 && unMes <= 12): "El mes debe estar entre 1 y 12.";
        if (unMes == 2)
        {
            if (unMes == 2 && esBisiesto(unAnho) == true)
            {
                return 29;
            }
            else 
            {
                return 28;
            }
        }
        else if ((unMes == 4) || (unMes == 6) || (unMes == 9) || (mes == 11))
        {
            return 30;
        }
        else 
        {
            return 31;
        }
    }

    
    /**
     * Decide si un año dado es bisiesto o no. Un año es bisiesto
     * si es múltiplo de 4, salvo los múltiplos de 100 que no son 
     * múltiplos de 400.
     * El parámetro debe corresponder a un año válido.
     */
    private boolean esBisiesto(int unAnho) 
    {
        // Implementar este método, reemplazando la línea siguiente
        assert(unAnho >= 1582);
        return ((unAnho%4==0 && unAnho%100==0) || (unAnho%400==0));
    }
    
    private boolean diaValido(int unDia, int unMes, int unAnho)
    {
        assert(postGregoriano(unDia, unMes, unAnho) == true): "El día debe ser igual o posterior al 15/10/1582.";
        assert(unDia >= 1 && unDia <= 31): "El dia debe estar entre 1 y 31.";
        assert(unMes >= 1 && unMes <= 12): "El mes debe estar entre 1 y 12.";
        if (unMes == 2)
        {
            if (unMes == 2 && esBisiesto(unAnho) == true)
            {
                assert(unDia <= 29): "El dia debe ser menor o igual a 29.";
            }
            else 
            {
                assert(unDia <= 28): "El dia debe ser menor o igual a 28.";
            }
        }
        else if ((unMes == 4) || (unMes == 6) || (unMes == 9) || (unMes == 11))
        {
            assert(unDia <= 30): "El dia debe ser menor o igual a 30.";
        }
        return true;
    }
    
    private boolean postGregoriano(int unDia, int unMes, int unAnho)
    {
        if ((unAnho<1582) || (unAnho == 1582 && unMes<10) || (unAnho == 1582 && unMes == 10 && unDia<15))
        {
            return false;
        }
        else 
        {
            return true;
        }
    }
    
    public boolean repOK(){
        if ((dia >= 1 && dia <= 31) && (mes >= 1 && mes <= 12) && postGregoriano(dia, mes, anho)){
            if (mes == 2){
                if (mes == 2 && esBisiesto(anho) == true){
                    return dia <= 29;
                } else {
                    return dia <= 28;
                }
            }
            else if ((mes == 4) || (mes == 6) || (mes == 9) || (mes == 11))
            {
                return dia <= 30;
            } else {
                return dia <= 31;
            }
        }
        return false;
    }
}