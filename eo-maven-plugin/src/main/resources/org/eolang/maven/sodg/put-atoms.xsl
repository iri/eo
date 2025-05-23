<?xml version="1.0" encoding="UTF-8"?>
<!--
 * SPDX-FileCopyrightText: Copyright (c) 2016-2025 Objectionary.com
 * SPDX-License-Identifier: MIT
-->
<xsl:stylesheet xmlns:xsl="http://www.w3.org/1999/XSL/Transform" xmlns:eo="https://www.eolang.org" id="put-atoms" version="2.0">
  <!--
  Here we attach atoms to vertices using ATOM instruction.
  -->
  <xsl:import href="/org/eolang/maven/sodg/_macros.xsl"/>
  <xsl:output encoding="UTF-8" method="xml"/>
  <xsl:template match="/program/sodg">
    <xsl:copy>
      <xsl:apply-templates select="node()|@*"/>
      <xsl:apply-templates select="/program/objects//o" mode="sodg"/>
    </xsl:copy>
  </xsl:template>
  <xsl:template match="o[@name and @atom and not(@base)]" mode="sodg" priority="1">
    <xsl:if test="not(@lambda)">
      <xsl:message terminate="yes">
        <xsl:text>The @lambda is absent at '</xsl:text>
        <xsl:value-of select="@loc"/>
        <xsl:text>'</xsl:text>
      </xsl:message>
    </xsl:if>
    <xsl:if test="@lambda = ''">
      <xsl:message terminate="yes">
        <xsl:text>The @lambda is empty at '</xsl:text>
        <xsl:value-of select="@loc"/>
        <xsl:text>'</xsl:text>
      </xsl:message>
    </xsl:if>
    <xsl:variable name="v">
      <xsl:value-of select="@loc"/>
      <xsl:text>.λ</xsl:text>
    </xsl:variable>
    <xsl:call-template name="i">
      <xsl:with-param name="name" select="'ADD'"/>
      <xsl:with-param name="args" as="item()*">
        <xsl:sequence>
          <xsl:value-of select="eo:var($v)"/>
        </xsl:sequence>
      </xsl:with-param>
      <xsl:with-param name="comment">
        <xsl:text>This is a lambda vertex for "</xsl:text>
        <xsl:value-of select="@loc"/>
        <xsl:text>"</xsl:text>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:call-template name="i">
      <xsl:with-param name="name" select="'BIND'"/>
      <xsl:with-param name="args" as="item()*">
        <xsl:sequence>
          <xsl:value-of select="eo:var(@loc)"/>
        </xsl:sequence>
        <xsl:sequence>
          <xsl:value-of select="eo:var($v)"/>
        </xsl:sequence>
        <xsl:sequence>
          <xsl:text>λ</xsl:text>
        </xsl:sequence>
      </xsl:with-param>
      <xsl:with-param name="comment">
        <xsl:text>This is an edge for the lambda vertex</xsl:text>
      </xsl:with-param>
    </xsl:call-template>
    <xsl:call-template name="i">
      <xsl:with-param name="name" select="'PUT'"/>
      <xsl:with-param name="args" as="item()*">
        <xsl:sequence>
          <xsl:value-of select="eo:var($v)"/>
        </xsl:sequence>
        <xsl:sequence>
          <xsl:variable name="data">
            <xsl:value-of select="replace(@lambda, ' ', '-')"/>
          </xsl:variable>
          <xsl:value-of select="$data"/>
        </xsl:sequence>
      </xsl:with-param>
      <xsl:with-param name="comment">
        <xsl:text>This is a lambda vertex for the atom returning "</xsl:text>
        <xsl:value-of select="@atom"/>
        <xsl:text>"</xsl:text>
      </xsl:with-param>
    </xsl:call-template>
  </xsl:template>
  <xsl:template match="o" mode="sodg">
    <!-- ignore them -->
  </xsl:template>
  <xsl:template match="node()|@*" mode="#default">
    <xsl:copy>
      <xsl:apply-templates select="node()|@*" mode="#current"/>
    </xsl:copy>
  </xsl:template>
</xsl:stylesheet>
