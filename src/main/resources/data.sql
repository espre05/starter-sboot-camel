-- ------------------------------------------------------------------------
-- Licensed to the Apache Software Foundation (ASF) under one or more
-- contributor license agreements. See the NOTICE file distributed with
-- this work for additional information regarding copyright ownership.
-- The ASF licenses this file to You under the Apache License, Version 2.0
-- (the 'License'); you may not use this file except in compliance with
-- the License. You may obtain a copy of the License at
--
-- http://www.apache.org/licenses/LICENSE-2.0
--
-- Unless required by applicable law or agreed to in writing, software
-- distributed under the License is distributed on an 'AS IS' BASIS,
-- WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
-- See the License for the specific language governing permissions and
-- limitations under the License.
-- ------------------------------------------------------------------------

INSERT INTO books (item, description)
  VALUES
      ('Camel',    'Camel in Action'),
      ('ActiveMQ', 'ActiveMQ in Action');
      
      
 
INSERT INTO orders (AMOUNT, PROCESSED, BOOK_ID)     
  VALUES
  	(4	,TRUE	, 1);
  	
INSERT INTO health_plan (policy_description, status, status_reason, comments)     
  VALUES
  	 ('hhp1'	, 'initial'	, null, 'user applying')
  	,('hhp1'	, 'renewal'	, null, 'renewing')
  	,('hhp1'	, 'rejected'	, 'renewal rejected; hh income not met', 'auto renewal reject')
  	,('hhp1'	, 'on hold'	, 'IRS income pending', 'user applying')
  	,('hhp1'	, 'on hold'	, 'Address invalid', 'user applying')
  	,('hhp1'	, 'rejected'	, 'primary person diseased, no longer applicable', 'user applying')
  	,('hhp1'	, 'approved'	, 'auto renewal', 'auto renewal approved')
  	,('hhp1'	, 'initial'	, null, 'user applying')
  	
  	;
