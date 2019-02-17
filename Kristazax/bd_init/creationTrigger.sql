--Auteur: Quentin Enjalbert
--Creation des triggers sur le table Compte

DROP TRIGGER IF EXISTS triggerModifCompte ON NOTE;

CREATE OR REPLACE FUNCTION modification_noteMoyenneCompte_et_nbNotes()
    RETURNs TRIGGER AS $$
DECLARE
    idUtilisateur   integer;
    var_note        integer;
    var_nbNotes     integer;
BEGIN
    IF (TG_OP = 'DELETE') THEN
        idUtilisateur = OLD.idVendeur;
    ELSE
        idUtilisateur = NEW.idVendeur;
    END IF;

    SELECT AVG(note) INTO var_note FROM NOTE WHERE idVendeur = idUtilisateur;
    SELECT COUNT(note) INTO var_nbNotes FROM NOTE WHERE idVendeur = idUtilisateur;

    UPDATE Compte
    SET noteMoyenneCompte = var_note,
        nbNotes = var_nbNotes
    WHERE idCompte = idUtilisateur;

    return null;
END;
$$ LANGUAGE plpgsql;

CREATE TRIGGER triggerModifCompte
AFTER INSERT OR UPDATE OR DELETE ON NOTE 
    FOR EACH ROW EXECUTE PROCEDURE modification_noteMoyenneCompte_et_nbNotes();
