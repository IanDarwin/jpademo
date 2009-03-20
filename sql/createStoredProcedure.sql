-- Create a trivial demo PostgreSQL stored procedure to find
-- Recordings that are out of stock.

CREATE OR REPLACE FUNCTION findOutOfStock() RETURNS SETOF record AS $$
DECLARE
    rec RECORD;
BEGIN
	FOR rec in select product_id,title,duration,product_id,index_number FROM music_tracks LOOP
		RETURN NEXT rec;
	END LOOP;
	return;
END
$$ LANGUAGE plpgsql;
