package br.com.wcorrea.transport.api.repository.filter;

public class LevelOfEducationFilter {

    private String globalFilter;
    private Integer page;
    private Integer size;
    private String sortField;
    private String sortOrder;

    private String name;
    private String description;
    private String degreeOfInstructionCaged;
    private String degreeOfInstructionSefip;
    private String degreeOfInstructionRais;

    public String getGlobalFilter() {
        return globalFilter;
    }

    public void setGlobalFilter(String globalFilter) {
        this.globalFilter = globalFilter;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getSize() {
        return size;
    }

    public void setSize(Integer size) {
        this.size = size;
    }

    public String getSortField() {
        return sortField;
    }

    public void setSortField(String sortField) {
        this.sortField = sortField;
    }

    public String getSortOrder() {
        return sortOrder;
    }

    public void setSortOrder(String sortOrder) {
        this.sortOrder = sortOrder;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDegreeOfInstructionCaged() {
        return degreeOfInstructionCaged;
    }

    public void setDegreeOfInstructionCaged(String degreeOfInstructionCaged) {
        this.degreeOfInstructionCaged = degreeOfInstructionCaged;
    }

    public String getDegreeOfInstructionSefip() {
        return degreeOfInstructionSefip;
    }

    public void setDegreeOfInstructionSefip(String degreeOfInstructionSefip) {
        this.degreeOfInstructionSefip = degreeOfInstructionSefip;
    }

    public String getDegreeOfInstructionRais() {
        return degreeOfInstructionRais;
    }

    public void setDegreeOfInstructionRais(String degreeOfInstructionRais) {
        this.degreeOfInstructionRais = degreeOfInstructionRais;
    }
}
