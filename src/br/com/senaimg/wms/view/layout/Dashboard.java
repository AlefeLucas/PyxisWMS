/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.senaimg.wms.view.layout;

import java.util.ArrayList;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Toggle;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;

/**
 *
 * @author Aluno
 */
public class Dashboard extends FlowPane {

    private ArrayList<DashboardItem> itens;
    private String title;

    /**
     *
     */
    public Dashboard() {
        this.setOrientation(Orientation.VERTICAL);
        this.setHgap(0);
        this.setVgap(0);
        this.setPadding(new Insets(10));
        itens = new ArrayList<>();

    }

    /**
     *
     * @param itens
     */
    public Dashboard(DashboardItem... itens) {
        this();
        for (DashboardItem item : itens) {
            this.add(item);
        }
    }

    /**
     *
     * @param itens
     */
    public Dashboard(ArrayList<DashboardItem> itens) {
        this();
        for (DashboardItem item : itens) {
            this.add(item);
        }
    }

    /**
     *
     * @param item
     */
    public void add(DashboardItem item) {
        itens.add(item);

        addToDash(item);

        item.getMenuItemBackward().setOnAction(e -> {
            moveBackward(item);
        });
        item.getMenuItemForward().setOnAction(e -> {
            moveForward(item);
        });
        item.getSizeGroup().selectedToggleProperty().addListener((o, oldValue, newValue) -> {
            changeSize(newValue, item);
        });

    }

    private void changeSize(Toggle newValue, DashboardItem item) {
        DashSize newSize = item.getSize();
        if (newValue == item.getRadioMenuBig()) {
            newSize = DashSize.BIG;
        } else if (newValue == item.getRadioMenuLarge()) {
            newSize = DashSize.LARGE;
        } else if (newValue == item.getRadioMenuMedium()) {
            newSize = DashSize.MEDIUM;
        } else if (newValue == item.getRadioMenuSmall()) {
            newSize = DashSize.SMALL;
        }

        item.setSize(newSize);

        cleanDash();

        addAllToDash(this.itens);
    }

    /**
     *
     * @param itens
     */
    public void addAll(DashboardItem... itens) {
        for (DashboardItem item : itens) {
            this.add(item);
        }
    }

    /**
     *
     * @param itens
     */
    public void addAll(ArrayList<DashboardItem> itens) {
        for (DashboardItem item : itens) {
            this.add(item);
        }
    }

    /**
     *
     * @param item
     */
    public void remove(DashboardItem item) {
        itens.remove(item);

        cleanDash();

        addAllToDash(this.itens);

    }

    /**
     *
     * @param itens
     */
    public void removeAll(DashboardItem... itens) {
        for (DashboardItem item : itens) {
            this.remove(item);
        }
    }

    private void addToDash(DashboardItem item) {
        DashSize size = item.getSize();
        ObservableList<Node> parents = this.getChildren();
        switch (size) {

            case BIG:
                setBigItem(item);
                break;

            case LARGE:
                setLargeItem(parents, item);
                break;

            case MEDIUM:
                setMediumItem(parents, item);
                break;

            case SMALL:
                setSmallItem(parents, item);
                break;

        }
    }

    private void addAllToDash(DashboardItem... itens) {
        for (DashboardItem item : itens) {
            this.addToDash(item);
        }
    }

    private void addAllToDash(ArrayList<DashboardItem> itens) {
        for (DashboardItem item : itens) {
            this.addToDash(item);
        }
    }

    private void cleanDash() {
        ObservableList<Node> children = this.getChildren();
        while (!children.isEmpty()) {
            children.remove(0);
        }
    }

    private void setSmallItem(ObservableList<Node> parents, Pane pane) {

        if (parents.isEmpty()) {

            FlowPane smallArea = newSmallArea(pane);
            newBigArea(smallArea);

        } else {
            Node last = parents.get(parents.size() - 1);

            if (last instanceof FlowPane) {
                FlowPane flow = (FlowPane) last;
                ObservableList<Node> children = flow.getChildren();
                if (children.isEmpty()) {

                    children.add(newSmallArea(pane));

                } else {
                    Node lastC = children.get(children.size() - 1);

                    if (lastC instanceof FlowPane) {
                        FlowPane flowSA = (FlowPane) lastC;
                        ObservableList<Node> grandChildren = flowSA.getChildren();
                        if (grandChildren.size() < 4) {
                            grandChildren.add(pane);
                        }

                    } else {

                        int numberBig = 0;
                        int numberSA = 0;
                        int numberLarge = 0;
                        int numberMedium = 0;
                        boolean finalLarge = false;

                        for (int x = 0; x < children.size(); x++) {
                            Node child = children.get(x);
                            if (child instanceof StackPane) {

                                StackPane bp = (StackPane) child;
                                double width = bp.getPrefWidth();
                                double height = bp.getPrefHeight();
                                if (width == 130 && height == 130) {
                                    numberMedium++;
                                } else if (width == 260 && height == 130) {
                                    numberLarge++;
                                    if (x != 0) {
                                        finalLarge = true;
                                    }
                                } else if (width == 260 && height == 260) {
                                    numberBig++;
                                }
                            } else if (child instanceof FlowPane) {
                                numberSA++;
                            }
                        }

                        if (numberBig != 0) {
                            FlowPane smallArea = newSmallArea(pane);
                            newBigArea(smallArea);

                        } else if (numberSA + numberMedium >= 4) {
                            FlowPane smallArea = newSmallArea(pane);

                            newBigArea(smallArea);

                        } else if (numberLarge >= 2) {
                            FlowPane smallArea = newSmallArea(pane);
                            newBigArea(smallArea);

                        } else if (numberLarge == 1 && numberSA + numberMedium >= 2) {
                            FlowPane smallArea = newSmallArea(pane);

                            newBigArea(smallArea);

                        } else if (finalLarge) {
                            FlowPane smallArea = newSmallArea(pane);
                            newBigArea(smallArea);

                        } else {
                            FlowPane smallArea = newSmallArea(pane);
                            children.add(smallArea);

                        }
                    }
                }
            }
        }
    }

    private void setMediumItem(ObservableList<Node> parents, Pane pane) {
        
        if (parents.isEmpty()) {
            newBigArea(pane);
        
        } else {
            Node last = parents.get(parents.size() - 1);

            if (last instanceof FlowPane) {

                FlowPane flow = (FlowPane) last;
                ObservableList<Node> children = flow.getChildren();
                
                if (children.isEmpty()) {
                    
                    children.add(pane);
                } else {
                    
                    int numberBig = 0;
                    int numberSA = 0;
                    int numberLarge = 0;
                    int numberMedium = 0;
                    boolean finalLarge = false;

                    for (int x = 0; x < children.size(); x++) {
                        Node child = children.get(x);
                        if (child instanceof StackPane) {
                            
                            StackPane bp = (StackPane) child;
                            double width = bp.getPrefWidth();
                            double height = bp.getPrefHeight();
                            
                            if (width == 130 && height == 130) {
                                numberMedium++;
                            } else if (width == 260 && height == 130) {
                                numberLarge++;
                                if (x != 0) {
                                    finalLarge = true;
                                }
                            } else if (width == 260 && height == 260) {
                                numberBig++;
                            }
                        } else if (child instanceof FlowPane) {
                            numberSA++;
                        }
                    }
                    if (numberBig != 0) {
                        newBigArea(pane);
                    } else if (numberSA + numberMedium >= 4) {
                        newBigArea(pane);
                    } else if (numberLarge >= 2) {
                        newBigArea(pane);
                    } else if (numberLarge == 1 && numberSA + numberMedium >= 2) {
                        newBigArea(pane);
                    } else if (finalLarge) {
                        newBigArea(pane);
                    } else {
                        children.add(pane);
                    }
                }

            }
        }
    }

    private void setLargeItem(ObservableList<Node> parents, Pane pane) {

        if (parents.isEmpty()) {

            newBigArea(pane);
        } else {
            Node last = parents.get(parents.size() - 1);

            if (last instanceof FlowPane) {
                FlowPane flow = (FlowPane) last;
                ObservableList<Node> children = flow.getChildren();
                
                if (children.isEmpty()) {

                    children.add(pane);
                } else {

                    int numberBig = 0;
                    int numberSA = 0;
                    int numberLarge = 0;
                    int numberMedium = 0;

                    for (Node child : children) {
                        if (child instanceof StackPane) {

                            StackPane bp = (StackPane) child;
                            double width = bp.getPrefWidth();
                            double height = bp.getPrefHeight();

                            if (width == 130 && height == 130) {
                                numberMedium++;
                            } else if (width == 260 && height == 130) {
                                numberLarge++;
                            } else if (width == 260 && height == 260) {
                                numberBig++;
                            }
                        } else if (child instanceof FlowPane) {
                            numberSA++;
                        }
                    }
                    if (numberBig != 0) {
                        newBigArea(pane);
                    } else if (numberSA + numberMedium >= 3) {
                        newBigArea(pane);
                    } else if (numberLarge >= 2) {
                        newBigArea(pane);
                    } else if (numberLarge >= 1 && numberSA + numberMedium >= 1) {
                        newBigArea(pane);
                    } else {
                        children.add(pane);
                    }
                }
            }
        }
    }

    private void setBigItem(Pane pane) {

        newBigArea(pane);
    }

    private void newBigArea(Pane pane) {

        FlowPane bigArea = createBigArea();
        bigArea.getChildren().add(pane);
        this.getChildren().add(bigArea);
    }

    private FlowPane newSmallArea(Pane pane) {

        FlowPane smallArea = createSmallArea();
        smallArea.getChildren().add(pane);
        return smallArea;
    }

    private FlowPane createBigArea() {
        FlowPane bigArea = new FlowPane(0, 0);
        double side = 260;
        bigArea.setMinSize(side, side);
        bigArea.setPrefSize(side, side);
        bigArea.setMaxSize(side, side);
        return bigArea;
    }

    private FlowPane createSmallArea() {
        FlowPane smallArea = new FlowPane(0, 0);
        double side = 130;
        smallArea.setMinSize(side, side);
        smallArea.setPrefSize(side, side);
        smallArea.setMaxSize(side, side);
        return smallArea;
    }

    private void moveBackward(DashboardItem source) {

        int previous = itens.indexOf(source);
        int next;

        next = getPredecessor(previous);

        DashboardItem placeholder = itens.get(next);
        itens.set(next, source);
        itens.set(previous, placeholder);

        cleanDash();

        addAllToDash(this.itens);
    }

    private int getPredecessor(int previous) {
        int next;
        if (previous == 0) {
            next = 0;
        } else {
            next = previous - 1;
        }
        return next;
    }

    private void moveForward(DashboardItem source) {

        int previous = itens.indexOf(source);
        int next;

        next = getSuccessor(previous);

        DashboardItem placeholder = itens.get(next);
        itens.set(next, source);
        itens.set(previous, placeholder);

        cleanDash();

        addAllToDash(this.itens);
    }

    private int getSuccessor(int previous) {
        int next;
        if (previous == itens.size() - 1) {
            next = itens.size() - 1;
        } else {
            next = previous + 1;
        }
        return next;
    }

    /**
     *
     * @return
     */
    public boolean isEmpty() {
        return itens.isEmpty();
    }
}
