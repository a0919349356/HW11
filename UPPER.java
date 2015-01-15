package myudfs;
import java.io.IOException;
import org.apache.pig.EvalFunc;
import org.apache.pig.data.Tuple;
import org.apache.pig.data.BagFactory;
import org.apache.pig.data.DataBag;
import org.apache.pig.data.TupleFactory;
import org.apache.pig.impl.logicalLayer.schema.Schema;
import org.apache.pig.data.DataType;
public class UPPER extends EvalFunc<DataBag>
{
	static String str;
	static int num;
	static int begin;
	static int end;
	static String[] name;
	static TupleFactory mTupleFactory = TupleFactory.getInstance();
    	static BagFactory mBagFactory = BagFactory.getInstance();

	public DataBag exec(Tuple input)throws IOException{
	
	if(input == null || input.size() == 0){
		return null;
	}
	num = 0;
	try{
		DataBag output = mBagFactory.newDefaultBag();
		str = (String)input.get(0);
		for(int i=0; i<str.length(); i++)
		{
			if(str.charAt(i) == '[')
				num++;
		}
		if(num < 2)
			return null;

		begin = str.indexOf('[', str.indexOf('[') + 1);
		end = str.indexOf(']');
		str =str.subSequence( begin , end+1 ).toString();
		name = str.split(",");
		if(!name[0].contains("\"")) return null;
		for(String d : name)
			output.add(mTupleFactory.newTuple(d.subSequence(d.indexOf('"')+1,d.indexOf('"',d.indexOf('"')+1))));// + ",";
		return output;//ans.subSequence(0,ans.length()-1).toString();	
	}catch(Exception e){
		throw new IOException("nooooo error!",e);
	}

	}
}
