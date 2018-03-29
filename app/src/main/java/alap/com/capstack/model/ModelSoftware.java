package alap.com.capstack.model;

import java.io.Serializable;

/**
 * Created by Android-2 on 28-03-2018.
 */

public class ModelSoftware implements Serializable {
    String name;
    String id;
    String prsn;
    String status;
    String mob_descr;
    String cost;
    String flow;
    String timeline;
    String prsn_cost;

    public String getMin_prsn() {
        return min_prsn;
    }

    public void setMin_prsn(String min_prsn) {
        this.min_prsn = min_prsn;
    }

    String min_prsn;

    public ModelSoftware() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPrsn() {
        return prsn;
    }

    public void setPrsn(String prsn) {
        this.prsn = prsn;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMob_descr() {
        return mob_descr;
    }

    public void setMob_descr(String mob_descr) {
        this.mob_descr = mob_descr;
    }

    public String getCost() {
        return cost;
    }

    public void setCost(String cost) {
        this.cost = cost;
    }

    public String getFlow() {
        return flow;
    }

    public void setFlow(String flow) {
        this.flow = flow;
    }

    public String getTimeline() {
        return timeline;
    }

    public void setTimeline(String timeline) {
        this.timeline = timeline;
    }

    public String getPrsn_cost() {
        return prsn_cost;
    }

    public void setPrsn_cost(String prsn_cost) {
        this.prsn_cost = prsn_cost;
    }
}
