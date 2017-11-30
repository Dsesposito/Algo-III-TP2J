package fiuba.algo3.tp2.model;
import fiuba.algo3.tp2.model.Cells.*;
import fiuba.algo3.tp2.model.Exceptions.CellNotFoundException;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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

        Railway train = CellsFactory.getInstanceOfTrain(this);
        Railway subway = CellsFactory.getInstanceOfSubway(this);
        CellGroup.group("Transporte ferroviario",Arrays.asList(train,subway));

        Service edesur = CellsFactory.getInstanceOfEdesur(this);
        Service aysa = CellsFactory.getInstanceOfAysa(this);
        CellGroup.group("Servicio de luz y agua",Arrays.asList(edesur,aysa));

        Neighborhood bsassur = CellsFactory.getInstanceOfBsAsSur(this);
        Neighborhood bsasnorte = CellsFactory.getInstanceOfBsAsNorte(this);
        NeighborhoodZone.group("Bs. As.",Arrays.asList(bsassur,bsasnorte));

        Neighborhood cordobaSur = CellsFactory.getInstanceOfCordobaSur(this);
        Neighborhood cordobaNorte = CellsFactory.getInstanceOfCordobaNorte(this);
        NeighborhoodZone.group("Cordoba",Arrays.asList(cordobaSur,cordobaNorte));

        Neighborhood saltaNorte = CellsFactory.getInstanceOfSaltaNorte(this);
        Neighborhood saltaSur = CellsFactory.getInstanceOfSaltaSur(this);
        NeighborhoodZone.group("Salta",Arrays.asList(saltaNorte,saltaSur));

        Neighborhood santaFe = CellsFactory.getInstanceOfSantaFe(this);
        NeighborhoodZone.group("Santa Fe",Arrays.asList(santaFe));

        Neighborhood neuquen = CellsFactory.getInstanceOfNeuquen(this);
        NeighborhoodZone.group("Neuquen",Arrays.asList(neuquen));

        Neighborhood tucuman = CellsFactory.getInstanceOfTucuman(this);
        NeighborhoodZone.group("Tucuman",Arrays.asList(tucuman));


        cells.add(CellsFactory.getInstanceOfStartPoint(this));
        cells.add(CellsFactory.getInstanceOfQuini6(this));
        cells.add(bsassur);
        cells.add(edesur);
        cells.add(bsasnorte);
        cells.add(CellsFactory.getInstanceOfJail(this));
        cells.add(cordobaSur);
        cells.add(CellsFactory.getInstanceOfDynamicForward(this));
        cells.add(subway);
        cells.add(cordobaNorte);
        cells.add(CellsFactory.getInstanceOfLuxuryTax(this));
        cells.add(santaFe);
        cells.add(aysa);
        cells.add(saltaNorte);
        cells.add(saltaSur);
        cells.add(CellsFactory.getInstanceOfPolicia(this));
        cells.add(train);
        cells.add(neuquen);
        cells.add(CellsFactory.getInstanceOfDynamicBackward(this));
        cells.add(tucuman);
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
