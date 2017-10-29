CREATE OR REPLACE FUNCTION get_state_count_on_orders()
  RETURNS SETOF RECORD AS $$
SELECT
  COUNT(o.state_id),
  s.name
FROM orders o
  INNER JOIN states s ON o.state_id = s.id
GROUP BY s.name;
$$ LANGUAGE SQL WITH ( ISCACHABLE );

CREATE OR REPLACE FUNCTION select_orders_by_user_login(login TEXT)
  RETURNS SETOF RECORD AS $$
DECLARE row RECORD;
BEGIN FOR
row IN SELECT *
       FROM orders o LEFT OUTER JOIN penalty p ON o.penalty_id = p.id
         INNER JOIN users u ON o.user_login = u.login
         INNER JOIN cars c ON o.car_id = c.id
       WHERE o.user_login = $1
LOOP RETURN NEXT row;
END LOOP;
  RETURN;
END;
$$ LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION select_all_from_orders()
  RETURNS SETOF RECORD AS '
DECLARE row RECORD;
BEGIN FOR
row IN SELECT *
       FROM orders o LEFT OUTER JOIN penalty p ON o.penalty_id = p.id
         INNER JOIN users u ON o.user_login = u.login
         INNER JOIN cars c ON o.car_id = c.id
LOOP RETURN NEXT row;
END LOOP;
  RETURN;
END;
' LANGUAGE 'plpgsql';

CREATE OR REPLACE FUNCTION select_orders_by_order_number(number INTEGER)
  RETURNS SETOF RECORD AS '
DECLARE row RECORD;
BEGIN FOR
row IN SELECT *
       FROM orders o LEFT OUTER JOIN penalty p ON o.penalty_id = p.id
         INNER JOIN users u ON o.user_login = u.login
         INNER JOIN cars c ON o.car_id = c.id AND o.number = $1
LOOP RETURN NEXT row;
END LOOP;
  RETURN;
END;
' LANGUAGE 'plpgsql' WITH ( ISSTRICT );