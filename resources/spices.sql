--
-- PostgreSQL database dump
--

-- Dumped from database version 14.5 (Ubuntu 14.5-0ubuntu0.22.04.1)
-- Dumped by pg_dump version 14.5 (Ubuntu 14.5-0ubuntu0.22.04.1)

SET statement_timeout = 0;
SET lock_timeout = 0;
SET idle_in_transaction_session_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SELECT pg_catalog.set_config('search_path', '', false);
SET check_function_bodies = false;
SET xmloption = content;
SET client_min_messages = warning;
SET row_security = off;

SET default_tablespace = '';

SET default_table_access_method = heap;

--
-- Name: countryorigin; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.countryorigin (
    country text NOT NULL
);


ALTER TABLE public.countryorigin OWNER TO usuario;

--
-- Name: cuisine; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.cuisine (
    geozone text NOT NULL
);


ALTER TABLE public.cuisine OWNER TO usuario;

--
-- Name: intercountry; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.intercountry (
    namespices character varying(100) NOT NULL,
    country text NOT NULL
);


ALTER TABLE public.intercountry OWNER TO usuario;

--
-- Name: intercuisine; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.intercuisine (
    namespices character varying(100) NOT NULL,
    geozone text NOT NULL
);


ALTER TABLE public.intercuisine OWNER TO usuario;

--
-- Name: interproduct; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.interproduct (
    namespices character varying(100) NOT NULL,
    format character varying(30) NOT NULL
);


ALTER TABLE public.interproduct OWNER TO usuario;

--
-- Name: productstyle; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.productstyle (
    format character varying(30) NOT NULL
);


ALTER TABLE public.productstyle OWNER TO usuario;

--
-- Name: spices; Type: TABLE; Schema: public; Owner: usuario
--

CREATE TABLE public.spices (
    name character varying(100) NOT NULL,
    introduction text,
    description text,
    ingredients text,
    basic_preparation text,
    recommended_applications text,
    cuisine text,
    product_style character varying(30),
    botanical_name character varying(50),
    fold character varying(20),
    notes character varying(150),
    shell_life text,
    bottle_style text,
    capacity_volume text,
    dimensions text,
    cap text,
    caffeine_free text,
    scoville_heat_scale text,
    handling_storage text,
    country_of_origin text,
    dietary_preferences text,
    allergen_information text,
    page_link text
);


ALTER TABLE public.spices OWNER TO usuario;

--
-- Data for Name: countryorigin; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.countryorigin (country) FROM stdin;
\.


--
-- Data for Name: cuisine; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.cuisine (geozone) FROM stdin;
\.


--
-- Data for Name: intercountry; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.intercountry (namespices, country) FROM stdin;
\.


--
-- Data for Name: intercuisine; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.intercuisine (namespices, geozone) FROM stdin;
\.


--
-- Data for Name: interproduct; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.interproduct (namespices, format) FROM stdin;
\.


--
-- Data for Name: productstyle; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.productstyle (format) FROM stdin;
\.


--
-- Data for Name: spices; Type: TABLE DATA; Schema: public; Owner: usuario
--

COPY public.spices (name, introduction, description, ingredients, basic_preparation, recommended_applications, cuisine, product_style, botanical_name, fold, notes, shell_life, bottle_style, capacity_volume, dimensions, cap, caffeine_free, scoville_heat_scale, handling_storage, country_of_origin, dietary_preferences, allergen_information, page_link) FROM stdin;
\.


--
-- Name: countryorigin countryorigin_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.countryorigin
    ADD CONSTRAINT countryorigin_pkey PRIMARY KEY (country);


--
-- Name: cuisine cuisine_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.cuisine
    ADD CONSTRAINT cuisine_pkey PRIMARY KEY (geozone);


--
-- Name: intercountry name_country_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.intercountry
    ADD CONSTRAINT name_country_pkey PRIMARY KEY (namespices, country);


--
-- Name: intercuisine name_cuisine_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.intercuisine
    ADD CONSTRAINT name_cuisine_pkey PRIMARY KEY (namespices, geozone);


--
-- Name: interproduct name_format_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.interproduct
    ADD CONSTRAINT name_format_pkey PRIMARY KEY (namespices, format);


--
-- Name: productstyle productstyle_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.productstyle
    ADD CONSTRAINT productstyle_pkey PRIMARY KEY (format);


--
-- Name: spices spices_pkey; Type: CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.spices
    ADD CONSTRAINT spices_pkey PRIMARY KEY (name);


--
-- Name: intercountry intercountry_country_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.intercountry
    ADD CONSTRAINT intercountry_country_fkey FOREIGN KEY (country) REFERENCES public.countryorigin(country) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: intercountry intercountry_namespices_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.intercountry
    ADD CONSTRAINT intercountry_namespices_fkey FOREIGN KEY (namespices) REFERENCES public.spices(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: intercuisine intercuisine_geozone_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.intercuisine
    ADD CONSTRAINT intercuisine_geozone_fkey FOREIGN KEY (geozone) REFERENCES public.cuisine(geozone) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: intercuisine intercuisine_namespices_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.intercuisine
    ADD CONSTRAINT intercuisine_namespices_fkey FOREIGN KEY (namespices) REFERENCES public.spices(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: interproduct interproduct_format_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.interproduct
    ADD CONSTRAINT interproduct_format_fkey FOREIGN KEY (format) REFERENCES public.productstyle(format) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- Name: interproduct interproduct_namespices_fkey; Type: FK CONSTRAINT; Schema: public; Owner: usuario
--

ALTER TABLE ONLY public.interproduct
    ADD CONSTRAINT interproduct_namespices_fkey FOREIGN KEY (namespices) REFERENCES public.spices(name) ON UPDATE CASCADE ON DELETE CASCADE;


--
-- PostgreSQL database dump complete
--

