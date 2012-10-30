package de.bielefeld.umweltamt.aui.mappings.basis;

import de.bielefeld.umweltamt.aui.mappings.StupidHelperClassWhichWillBeGoneSoon;

public abstract class AbstractBasisPrioritaet extends
    StupidHelperClassWhichWillBeGoneSoon implements java.io.Serializable {

    private static final long serialVersionUID = 7187397059108121923L;
    private BasisPrioritaetId id;
    private BasisStandort basisStandort;
    private BasisBetreiber basisBetreiber;
    private Integer prioritaet;

    public AbstractBasisPrioritaet() {
    }

    public AbstractBasisPrioritaet(BasisPrioritaetId id,
        BasisStandort basisStandort, BasisBetreiber basisBetreiber) {
        this.id = id;
        this.basisStandort = basisStandort;
        this.basisBetreiber = basisBetreiber;
    }

    public AbstractBasisPrioritaet(BasisPrioritaetId id,
        BasisStandort basisStandort, BasisBetreiber basisBetreiber,
        Integer prioritaet) {
        this.id = id;
        this.basisStandort = basisStandort;
        this.basisBetreiber = basisBetreiber;
        this.prioritaet = prioritaet;
    }

    public BasisPrioritaetId getId() {
        return this.id;
    }

    public void setId(BasisPrioritaetId id) {
        this.id = id;
    }

    public BasisStandort getBasisStandort() {
        return this.basisStandort;
    }

    public void setBasisStandort(BasisStandort basisStandort) {
        this.basisStandort = basisStandort;
    }

    public BasisBetreiber getBasisBetreiber() {
        return this.basisBetreiber;
    }

    public void setBasisBetreiber(BasisBetreiber basisBetreiber) {
        this.basisBetreiber = basisBetreiber;
    }

    public Integer getPrioritaet() {
        return this.prioritaet;
    }

    public void setPrioritaet(Integer prioritaet) {
        this.prioritaet = prioritaet;
    }

    // TODO: Why is there no equals and hashCode method here?
}
