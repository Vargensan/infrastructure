package com.waf.infrastructure.rsql;

import com.github.tennaito.rsql.misc.ArgumentParser;
import com.github.tennaito.rsql.misc.Mapper;

/**
 * Facade Interface for miscellaneous utilities for operation definition.
 * <p>
 * PropertyMapper for translation of properties aliases.
 * ArgumentParser for casting specific types.
 * PredicateBuilder for RSQL AST new nodes.
 *
 * @author AntonioRabelo
 * @since 2015-02-03
 */
@SuppressWarnings("unused")
public interface BuilderTools {

    /**
     * Get a mapper for the properties.
     *
     * @return Mapper
     */
    Mapper getPropertiesMapper();

    /**
     * Set a specific Mapper.
     *
     * @param mapper Mapper
     */
    void setPropertiesMapper(Mapper mapper);

    /**
     * Get an argument parser for casting types.
     *
     * @return ArgumentParser
     */
    ArgumentParser getArgumentParser();

    /**
     * Set a specific ArgumentParser.
     *
     * @param argumentParser ArgumentParser
     */
    void setArgumentParser(ArgumentParser argumentParser);

    /**
     * Get a predicate strategy for parsing Node into Predicate.
     *
     * @return PredicateBuilderStrategy
     */
    PredicateBuilderStrategy getPredicateBuilder();

    /**
     * Set a specific predicate strategy.
     *
     * @param predicateStrategy PredicateBuilderStrategy
     */
    void setPredicateBuilder(PredicateBuilderStrategy predicateStrategy);
}