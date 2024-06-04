-- Table: public.minha-lista

-- DROP TABLE IF EXISTS public."minha-lista";

CREATE TABLE IF NOT EXISTS public."minha-lista"
(
    id bigint NOT NULL GENERATED BY DEFAULT AS IDENTITY ( INCREMENT 1 START 1 MINVALUE 1 MAXVALUE 9223372036854775807 CACHE 1 ),
    quantidade numeric(12,3) NOT NULL,
    comentario character varying(255) COLLATE pg_catalog."default" NOT NULL,
    produto_descricao character varying(255) COLLATE pg_catalog."default" NOT NULL,
    produto_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    usuario_id character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT "minha-lista_pkey" PRIMARY KEY (id)
)

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."minha-lista"
    OWNER to admin;