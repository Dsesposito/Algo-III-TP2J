package fiuba.algo3.tp2.model;
import fiuba.algo3.tp2.Global;
import fiuba.algo3.tp2.model.Cells.*;
import fiuba.algo3.tp2.model.Exceptions.CellNotFoundException;

import java.util.*;

public class Board {

    private List<Cell> cells;

    public Board() {
        cells = new ArrayList<>();
        this.initBoardCells();
    }

    public Cell getCellXPositionsFurtherForward(Cell cell, Long numberOfCellsToMoveForward){
        if(!cells.contains(cell)){
            throw new CellNotFoundException("The cell " + cell.getName() + " is invalid.");
        }

        int index = cells.indexOf(cell) + Math.toIntExact(numberOfCellsToMoveForward);
        int lastPos = cells.size()-1;
        if(index > lastPos){
            index = (index % lastPos) - 1;
        }

        return cells.get(index);
    }

    public Cell getCellXPositionsFurtherBackward(Cell cell, Long numberOfCellsToMoveBackward){
        if(!cells.contains(cell)){
            throw new CellNotFoundException("The cell " + cell.getName() + " is invalid.");
        }

        return cells.get((int) (cells.indexOf(cell) - numberOfCellsToMoveBackward));
    }

    public Cell getStartCell(){
        return cells.get(0);
    }

    public List getCells(){
        return this.cells;
    }

    public Quini6 getQuini6(){
        return (Quini6) cells.stream().filter(cell -> cell.getName().equals("Quini 6")).findFirst().orElse(null);
    }

    public Jail getJail(){
        return (Jail) cells.stream().filter(cell -> cell.getName().equals("Carcel")).findFirst().orElse(null);
    }

    public Police getPolice(){
        return (Police) cells.stream().filter(cell -> cell.getName().equals("Policia")).findFirst().orElse(null);
    }

    public LuxuryTax getLuxuryTax(){
        return (LuxuryTax) cells.stream().filter(cell -> cell.getName().equals("Impuesto Al Lujo")).findFirst().orElse(null);
    }

    public DynamicForward getDynamicForward(){
        return (DynamicForward) cells.stream().filter(cell -> cell.getName().equals("Avance Dinámico")).findFirst().orElse(null);
    }

    public DynamicBackward getDynamicBackward(){
        return (DynamicBackward) cells.stream().filter(cell -> cell.getName().equals("Retroceso Dinámico")).findFirst().orElse(null);
    }

    public Neighborhood getNeighborhoodByName(String name){
        return (Neighborhood) cells.stream().filter(cell -> cell.getName().equals(name)).findFirst().orElse(null);
    }


    private void initBoardCells() {

        Cell startPoint = CellsFactory.getInstanceOfStartPoint(this);
        cells.add(startPoint);
        Cell quini6 = CellsFactory.getInstanceOfQuini6(this);
        cells.add(quini6);
        Cell bsAsSur = CellsFactory.getInstanceOfBsAsSur(this);
        cells.add(bsAsSur);
        Cell edesur = CellsFactory.getInstanceOfEdesur(this);
        cells.add(edesur);
        Cell bsAsNorte = CellsFactory.getInstanceOfBsAsNorte(this);
        cells.add(bsAsNorte);
        Cell jail = CellsFactory.getInstanceOfJail(this);
        cells.add(jail);
        Cell cordobaSur = CellsFactory.getInstanceOfCordobaSur(this);
        cells.add(cordobaSur);
        Cell dinamicForward = CellsFactory.getInstanceOfDynamicForward(this);
        cells.add(dinamicForward);
        Cell subway = CellsFactory.getInstanceOfSubway(this);
        cells.add(subway);
        Cell cordobaNorte = CellsFactory.getInstanceOfCordobaNorte(this);
        cells.add(cordobaNorte);
        Cell luxuryTax = CellsFactory.getInstanceOfLuxuryTax(this);
        cells.add(luxuryTax);
        Cell santaFe = CellsFactory.getInstanceOfSantaFe(this);
        cells.add(santaFe);
        Cell aysa = CellsFactory.getInstanceOfAysa(this);
        cells.add(aysa);
        Cell saltaNorte = CellsFactory.getInstanceOfSaltaNorte(this);
        cells.add(saltaNorte);
        Cell saltaSur = CellsFactory.getInstanceOfSaltaSur(this);
        cells.add(saltaSur);
        Cell policia = CellsFactory.getInstanceOfPolicia(this);
        cells.add(policia);
        Cell train = CellsFactory.getInstanceOfTrain(this);
        cells.add(train);
        Cell neuquen = CellsFactory.getInstanceOfNeuquen(this);
        cells.add(neuquen);
        Cell dinamicaBackard = CellsFactory.getInstanceOfDynamicBackward(this);
        cells.add(dinamicaBackard);
        Cell tucuman = CellsFactory.getInstanceOfTucuman(this);
        cells.add(tucuman);

        CellGroup.group("Transporte ferroviario",Arrays.asList((Railway)train, (Railway)subway));
        CellGroup.group("Servicio de luz y agua",Arrays.asList((Service)edesur, (Service)aysa));
        NeighborhoodZone.group("Bs. As.",Arrays.asList((Neighborhood)bsAsSur, (Neighborhood)bsAsNorte));
        NeighborhoodZone.group("Cordoba", Arrays.asList((Neighborhood)cordobaSur, (Neighborhood)cordobaNorte));
        NeighborhoodZone.group("Salta",Arrays.asList((Neighborhood)saltaNorte, (Neighborhood)saltaSur));
        NeighborhoodZone.group("Santa Fe",Arrays.asList((Neighborhood)santaFe));
        NeighborhoodZone.group("Neuquen",Arrays.asList((Neighborhood)neuquen));
        NeighborhoodZone.group("Tucuman",Arrays.asList((Neighborhood)tucuman));

    }

    public Service getServiceByName(String name){
        return (Service) cells.stream()
                .filter(cell -> cell.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    public Railway getRailwayByName(String name){
        return (Railway) cells.stream()
                .filter(cell -> cell.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
    public Cell getCellByName(String name){
        return cells.stream().filter(cell -> cell.getName().equals(name))
                .findFirst()
                .orElse(null);
    }
}
