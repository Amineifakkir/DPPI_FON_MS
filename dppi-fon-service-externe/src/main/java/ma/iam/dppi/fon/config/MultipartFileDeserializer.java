package ma.iam.dppi.fon.config;

import java.io.IOException;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItem;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartFile;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.StdDeserializer;


@Configuration
public class MultipartFileDeserializer extends StdDeserializer<MultipartFile> {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	protected MultipartFileDeserializer() {
        super(MultipartFile.class);
    }

	@Override
	public MultipartFile deserialize(JsonParser p, DeserializationContext ctxt) throws IOException, JacksonException {
		 FileItem fileItem = new DiskFileItem("file", "application/octet-stream", false, p.getText(), 0, null);
	        return new CommonsMultipartFile(fileItem);
	}

    
}
