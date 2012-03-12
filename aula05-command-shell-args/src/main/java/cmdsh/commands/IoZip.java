package cmdsh.commands;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.zip.GZIPOutputStream;

import cmdsh.args.ArgWithoutNameMandatory;
import cmdsh.args.ArgWithoutNameOptional;
import cmdsh.core.AbstractCommand;
import cmdsh.core.IArgument;

public class IoZip extends AbstractCommand{
	private IArgument<String> inArg, outArg;
	public IoZip() {
		super("io zip",
				new IArgument[]{
					new ArgWithoutNameMandatory("inFile"),
					new ArgWithoutNameOptional("outFile")});
	}
	@Override
	protected void executeCommand(){
		String inPath = inArg.getValue();
		String outPath = outArg.getValue();
		if(outPath == null){ 
			// If the user did not specify an output file name, 
			// then we use the name of the input file.
			int idxDot = inPath.lastIndexOf('.');
			if(idxDot >= 0){outPath = inPath.substring(0,idxDot) + ".rar";}
			else{outPath = inPath + ".rar";}
		}
		try {
			InputStream in = new FileInputStream(new File(inPath));
			OutputStream out;
			out = new GZIPOutputStream(new FileOutputStream(outPath));
			int c;
			while ((c = in.read()) != -1)
				out.write(c);
			in.close(); out.close();
		} 
		catch (FileNotFoundException e) {e.printStackTrace();}
		catch (IOException e) {e.printStackTrace();}
	}
}
