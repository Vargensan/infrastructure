package com.waf.infrastructure.rsql;

import com.github.tennaito.rsql.misc.ArgumentParser;
import com.github.tennaito.rsql.misc.DefaultArgumentParser;
import com.github.tennaito.rsql.misc.Mapper;
import com.github.tennaito.rsql.misc.SimpleMapper;

/**
 * Simple Builder Tools
 * <p>
 * A facade for all tools that are used inside the build.
 *
 * @author AntonioRabelo
 * @since 2015-02-13
 */
public class SimpleBuilderTools implements BuilderTools {

    private Mapper mapper;
    private ArgumentParser argumentParser;
    private PredicateBuilderStrategy delegate;


    /* (non-Javadoc)
     * @see br.tennaito.rsql.builder.BuilderTools#setPropertiesMapper(br.tennaito.rsql.misc.Mapper)
     */
    public void setPropertiesMapper(Mapper mapper) {
        this.mapper = mapper;
    }

    /* (non-Javadoc)
     * @see br.tennaito.rsql.builder.BuilderTools#setArgumentParser(br.tennaito.rsql.misc.ArgumentParser)
     */
    public void setArgumentParser(ArgumentParser argumentParser) {
        this.argumentParser = argumentParser;
    }

    /* (non-Javadoc)
     * @see br.tennaito.rsql.builder.BuilderTools#setPredicateBuilder(br.tennaito.rsql.jpa.PredicateBuilderStrategy)
     */
    public void setPredicateBuilder(PredicateBuilderStrategy predicateStrategy) {
        this.delegate = predicateStrategy;
    }

    /* (non-Javadoc)
     * @see br.tennaito.rsql.builder.BuilderTools#getPropertiesMapper()
     */
    public Mapper getPropertiesMapper() {
        if (this.mapper == null) {
            this.mapper = new SimpleMapper();
        }
        return this.mapper;
    }

    /* (non-Javadoc)
     * @see br.tennaito.rsql.builder.BuilderTools#getArgumentParser()
     */
    public ArgumentParser getArgumentParser() {
        if (this.argumentParser == null) {
            this.argumentParser = new DefaultArgumentParser();
        }
        return this.argumentParser;
    }

    /* (non-Javadoc)
     * @see br.tennaito.rsql.builder.BuilderTools#getPredicateBuilder()
     */
    public PredicateBuilderStrategy getPredicateBuilder() {
        return this.delegate;
    }
}